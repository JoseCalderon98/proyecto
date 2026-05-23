package application.usecases;

import application.adapters.api.requests.DuplicateBankAccountException;
import application.adapters.api.requests.InactiveClientException;
import application.adapters.api.requests.InactiveUserException;
import application.adapters.api.requests.UnauthorizedRoleException;
import domain.ports.user.UserPort;
import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;

import domain.models.account.BankAccount;
import domain.models.account.AccountStatus;
import domain.models.client.IndividualClient;
import domain.models.client.CorporateClient;
import domain.models.client.ClientStatus;
import domain.ports.account.BankAccountPort;
import domain.ports.client.IndividualClientPort;
import domain.ports.client.CorporateClientPort;
import domain.services.CreateBankAccount;
import java.time.LocalDateTime;

public class CreateBankAccountUseCase {

    private CreateBankAccount createBankAccountDomainService;
    private BankAccountPort bankAccountPort;
    private IndividualClientPort clientPort;
    private CorporateClientPort corporateClientPort;
    private UserPort userPort;

    public void setCreateBankAccountDomainService(CreateBankAccount createBankAccountDomainService) {
        this.createBankAccountDomainService = createBankAccountDomainService;
    }

    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void setClientPort(IndividualClientPort clientPort) {
        this.clientPort = clientPort;
    }

    public void setCorporateClientPort(CorporateClientPort corporateClientPort) {
        this.corporateClientPort = corporateClientPort;
    }

    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public BankAccount execute(BankAccount account) {
        // Enforce Rule 3.1: Unique account number
        if (bankAccountPort.findById(account.getAccountNumber()).isPresent()) {
            throw new DuplicateBankAccountException("Account number already exists");
        }

        // Enforce Rule 3.6: Account opening only for active clients (Individual or Corporate)
        String holderId = account.getHolderId();
        ClientStatus clientStatus = findClientStatus(holderId);

        if (clientStatus != ClientStatus.ACTIVE) {
            throw new InactiveClientException("Account can only be opened for active clients");
        }

        // Enforce Rule 1.7 & Role Requirement: Holder must have a registered and active User account with a client role
        User holderUser = userPort.findAll().stream()
                .filter(u -> u.getIdentification().equals(holderId))
                .findFirst()
                .orElseThrow(() -> new InactiveUserException("No user account registered for this holder identification"));

        if (holderUser.getUserStatus() != UserStatus.ACTIVE) {
            throw new InactiveUserException("User status must be ACTIVE to open bank accounts");
        }

        if (holderUser.getSystemRole() != UserRole.NATURAL_CLIENT && holderUser.getSystemRole() != UserRole.ENTERPRISE_CLIENT) {
            throw new UnauthorizedRoleException("Only users with role NATURAL_CLIENT or ENTERPRISE_CLIENT can open bank accounts");
        }

        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setOpeningDate(LocalDateTime.now());

        return createBankAccountDomainService.execute(account);
    }

    /**
     * Searches for the client status across both Individual and Corporate client repositories.
     * Throws InactiveClientException if the client is not found in either repository.
     */
    private ClientStatus findClientStatus(String holderId) {
        // Search in Individual Clients
        java.util.Optional<IndividualClient> individualClient = clientPort.findAll().stream()
                .filter(c -> c.getIdentification().equals(holderId) || String.valueOf(c.getId()).equals(holderId))
                .findFirst();

        if (individualClient.isPresent()) {
            return individualClient.get().getClientStatus();
        }

        // Search in Corporate Clients
        java.util.Optional<CorporateClient> corporateClient = corporateClientPort.findAll().stream()
                .filter(c -> c.getIdentification().equals(holderId) || String.valueOf(c.getId()).equals(holderId))
                .findFirst();

        if (corporateClient.isPresent()) {
            return corporateClient.get().getClientStatus();
        }

        throw new InactiveClientException("Client not found with the given identification or ID");
    }
}
