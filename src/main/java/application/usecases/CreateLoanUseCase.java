package application.usecases;

import application.adapters.api.requests.InactiveClientException;
import application.adapters.api.requests.InactiveUserException;
import application.adapters.api.requests.UnauthorizedRoleException;
import application.adapters.api.requests.BankAccountNotFoundException;
import application.adapters.api.requests.InactiveBankAccountException;
import application.adapters.api.requests.UnauthorizedOwnerException;

import domain.models.loan.Loan;
import domain.models.loan.LoanStatus;
import domain.models.client.IndividualClient;
import domain.models.client.CorporateClient;
import domain.models.client.ClientStatus;
import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;
import domain.ports.loan.LoanPort;
import domain.ports.client.IndividualClientPort;
import domain.ports.client.CorporateClientPort;
import domain.ports.user.UserPort;
import domain.ports.account.BankAccountPort;
import domain.models.account.BankAccount;
import domain.models.account.AccountStatus;
import domain.services.CreateLoan;

public class CreateLoanUseCase {

    private CreateLoan createLoanDomainService;
    private LoanPort loanPort;
    private IndividualClientPort clientPort;
    private CorporateClientPort corporateClientPort;
    private UserPort userPort;
    private BankAccountPort bankAccountPort;

    public void setCreateLoanDomainService(CreateLoan createLoanDomainService) {
        this.createLoanDomainService = createLoanDomainService;
    }

    public void setLoanPort(LoanPort loanPort) {
        this.loanPort = loanPort;
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

    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public Loan execute(Loan loan, int creatorUserId, UserRole creatorRole) {
        // Enforce Rule 4.1: Loan must belong to a valid and active client (Individual or Corporate)
        String applicantId = loan.getApplicantClientId();
        ClientStatus applicantStatus = findClientStatus(applicantId);

        if (applicantStatus != ClientStatus.ACTIVE) {
            throw new InactiveClientException("Loans can only be requested for active clients");
        }

        // Enforce Rule 1.7 & Role Requirement: Applicant must have a registered and active User account with a client role
        User applicantUser = userPort.findAll().stream()
                .filter(u -> u.getIdentification().equals(applicantId))
                .findFirst()
                .orElseThrow(() -> new InactiveUserException("No user account registered for this applicant identification"));

        if (applicantUser.getUserStatus() != UserStatus.ACTIVE) {
            throw new InactiveUserException("Applicant user status must be ACTIVE to request loans");
        }

        if (applicantUser.getSystemRole() != UserRole.NATURAL_CLIENT && applicantUser.getSystemRole() != UserRole.ENTERPRISE_CLIENT) {
            throw new UnauthorizedRoleException("Only users with role NATURAL_CLIENT or ENTERPRISE_CLIENT can request loans as applicants");
        }

        // Enforce Destination Bank Account Requirement: Destination account for disbursement must exist, be ACTIVE, and belong to the applicant
        String destAccountNum = loan.getDestinationAccountForDisbursement();
        if (destAccountNum == null || destAccountNum.trim().isEmpty()) {
            throw new BankAccountNotFoundException("Destination bank account for disbursement is required");
        }

        BankAccount destAccount = bankAccountPort.findById(destAccountNum)
                .orElseThrow(() -> new BankAccountNotFoundException("Destination bank account for disbursement does not exist"));

        if (destAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new InactiveBankAccountException("Destination bank account for disbursement must be ACTIVE");
        }

        if (!destAccount.getHolderId().equals(applicantId)) {
            throw new UnauthorizedOwnerException("Destination bank account for disbursement does not belong to the applicant client");
        }

        // Enforce Rule 1.7: Executing user must be ACTIVE (not inactive or blocked)
        User creatorUser = userPort.findById(creatorUserId)
                .orElseThrow(() -> new InactiveUserException("Creator user not found in the system"));

        if (creatorUser.getUserStatus() != UserStatus.ACTIVE) {
            throw new InactiveUserException("Inactive or blocked users cannot request banking products");
        }

        // Enforce Rule 5.2: Allowed roles to request loans
        if (creatorRole != UserRole.NATURAL_CLIENT && 
            creatorRole != UserRole.ENTERPRISE_CLIENT && 
            creatorRole != UserRole.COMMERCIAL_EXECUTIVE) {
            throw new UnauthorizedRoleException("Only clients (Natural/Enterprise) and Commercial Executives are allowed to request loans");
        }

        // Enforce Rules 4.2, 4.3, 4.4: Trigger domain validations (monto, tasa, plazo)
        loan.validate();

        // Enforce Rule 5.1: Starts in "En estudio" (PENDING)
        loan.setLoanStatus(LoanStatus.PENDING);
        loan.setCreatorUserId(creatorUserId);

        return createLoanDomainService.execute(loan);
    }

    /**
     * Searches for the client status across both Individual and Corporate client repositories.
     * Throws InactiveClientException if the client is not found in either repository.
     */
    private ClientStatus findClientStatus(String applicantId) {
        // Search in Individual Clients
        java.util.Optional<IndividualClient> individualClient = clientPort.findAll().stream()
                .filter(c -> c.getIdentification().equals(applicantId) || String.valueOf(c.getId()).equals(applicantId))
                .findFirst();

        if (individualClient.isPresent()) {
            return individualClient.get().getClientStatus();
        }

        // Search in Corporate Clients
        java.util.Optional<CorporateClient> corporateClient = corporateClientPort.findAll().stream()
                .filter(c -> c.getIdentification().equals(applicantId) || String.valueOf(c.getId()).equals(applicantId))
                .findFirst();

        if (corporateClient.isPresent()) {
            return corporateClient.get().getClientStatus();
        }

        throw new InactiveClientException("Applicant client not found in the system");
    }
}
