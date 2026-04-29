package application.usecases;

import domain.events.TransferCreatedEvent;
import domain.models.account.BankAccount;
import domain.models.transfer.Transfer;
import domain.models.transfer.TransferStatus;
import domain.models.user.UserRole;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateTransferUseCase {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;
    private final EventPublisher eventPublisher;
    private final BigDecimal enterpriseApprovalThreshold;

    public CreateTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher, BigDecimal enterpriseApprovalThreshold) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
        this.eventPublisher = eventPublisher;
        this.enterpriseApprovalThreshold = enterpriseApprovalThreshold;
    }

    public void execute(String originAccountNumber, String destinationAccountNumber, BigDecimal amount, int creatorUserId, UserRole creatorRole) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }

        boolean requiresApproval = false;
        
        if (creatorRole == UserRole.ENTERPRISE_EMPLOYEE) {
            if (amount.compareTo(enterpriseApprovalThreshold) > 0) {
                requiresApproval = true;
            }
        } else if (creatorRole != UserRole.NATURAL_CLIENT && creatorRole != UserRole.ENTERPRISE_CLIENT) {
            throw new SecurityException("Role not authorized to create transfers");
        }

        Transfer transfer = new Transfer(
            0, // Assigned by DB
            originAccountNumber,
            destinationAccountNumber,
            amount,
            LocalDateTime.now(),
            null,
            requiresApproval ? TransferStatus.PENDING : TransferStatus.COMPLETED,
            creatorUserId,
            0
        );

        if (!requiresApproval) {
            BankAccount originAccount = bankAccountPort.findById(originAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Origin account not found"));
            BankAccount destinationAccount = bankAccountPort.findById(destinationAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

            originAccount.withdraw(amount);
            destinationAccount.deposit(amount);

            bankAccountPort.save(originAccount);
            bankAccountPort.save(destinationAccount);
        }

        Transfer savedTransfer = transferPort.save(transfer);
        eventPublisher.publish(new TransferCreatedEvent(savedTransfer, creatorUserId, creatorRole.name()));
    }
}
