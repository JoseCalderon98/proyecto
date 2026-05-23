package application.usecases;

import application.adapters.api.requests.InactiveUserException;

import domain.events.TransferCreatedEvent;
import domain.models.account.BankAccount;
import domain.models.transfer.Transfer;
import domain.models.transfer.TransferStatus;
import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;
import domain.ports.user.UserPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class CreateTransferUseCase {

    private TransferPort transferPort;
    private BankAccountPort bankAccountPort;
    private EventPublisher eventPublisher;
    private BigDecimal enterpriseApprovalThreshold;
    private UserPort userPort;

    public void setTransferPort(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setEnterpriseApprovalThreshold(BigDecimal enterpriseApprovalThreshold) {
        this.enterpriseApprovalThreshold = enterpriseApprovalThreshold;
    }

    public Transfer execute(String originAccountNumber, String destinationAccountNumber, BigDecimal amount, int creatorUserId, UserRole creatorRole) {
        // Enforce Rule 1.7: Executing user must be ACTIVE (not inactive or blocked)
        User creatorUser = userPort.findById(creatorUserId)
                .orElseThrow(() -> new InactiveUserException("Creator user not found in the system"));

        if (creatorUser.getUserStatus() != UserStatus.ACTIVE) {
            throw new InactiveUserException("Inactive or blocked users cannot create transfers");
        }

        boolean requiresApproval = false;
        
        // Rules for Enterprise Transfers (Rules 7 and 8)
        if (creatorRole == UserRole.ENTERPRISE_CLIENT) {
            // New Requirement: Client (Owner) always needs approval
            requiresApproval = true;
        } else if (creatorRole == UserRole.ENTERPRISE_EMPLOYEE || 
                   creatorRole == UserRole.ENTERPRISE_SUPERVISOR) {
            
            if (amount.compareTo(enterpriseApprovalThreshold) > 0) {
                requiresApproval = true;
            }
        } else if (creatorRole != UserRole.NATURAL_CLIENT) {
            // General security check for roles not explicitly allowed to transfer
            throw new SecurityException("Role not authorized to create transfers");
        }

        Transfer transfer = new Transfer();
        transfer.setOriginAccount(originAccountNumber);
        transfer.setDestinationAccount(destinationAccountNumber);
        transfer.setAmount(amount);
        transfer.setCreationDate(LocalDateTime.now());
        transfer.setTransferStatus(requiresApproval ? TransferStatus.PENDING_APPROVAL : TransferStatus.EXECUTED);
        transfer.setCreatorUserId(creatorUserId);

        BankAccount originAccount = bankAccountPort.findById(originAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Origin account not found"));

        // Enforce Account Ownership Security: Origin account must belong to the user
        String userIdent = creatorUser.getIdentification();
        String userRelated = creatorUser.getRelatedId();
        String accountHolder = originAccount.getHolderId();

        if (creatorRole != UserRole.INTERNAL_ANALYST) {
            boolean isOwner = (accountHolder.equals(userIdent) || (userRelated != null && accountHolder.equals(userRelated)));
            if (!isOwner) {
                throw new SecurityException("Unauthorized: You don't own the origin account '" + originAccountNumber + "'");
            }
        }
        
        if (originAccount.getAccountStatus() != domain.models.account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("Origin account is not active");
        }

        if (!requiresApproval) {
            BankAccount destinationAccount = bankAccountPort.findById(destinationAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

            originAccount.withdraw(amount);
            destinationAccount.deposit(amount);

            bankAccountPort.save(originAccount);
            bankAccountPort.save(destinationAccount);
        }

        Transfer savedTransfer = transferPort.save(transfer);

        // Publish event
        TransferCreatedEvent event = new TransferCreatedEvent();
        event.setUserId(creatorUserId);
        event.setUserRole(creatorRole.name());
        event.setAffectedProductId(String.valueOf(savedTransfer.getTransferId()));
        event.setEventData(Map.of(
            "amount", savedTransfer.getAmount(),
            "originAccount", savedTransfer.getOriginAccount(),
            "destinationAccount", savedTransfer.getDestinationAccount(),
            "status", savedTransfer.getTransferStatus().name()
        ));
        eventPublisher.publish(event);

        return savedTransfer;
    }
}
