package application.usecases;

import domain.events.TransferApprovedEvent;
import domain.models.account.BankAccount;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;

public class ApproveTransferUseCase {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;
    private final EventPublisher eventPublisher;

    public ApproveTransferUseCase(TransferPort transferPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
        this.eventPublisher = eventPublisher;
    }

    public void execute(int transferId, int approverUserId, UserRole approverRole) {
        if (approverRole != UserRole.ENTERPRISE_SUPERVISOR) {
            throw new SecurityException("Only an Enterprise Supervisor can approve transfers");
        }

        Transfer transfer = transferPort.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        BankAccount originAccount = bankAccountPort.findById(transfer.getOriginAccount())
                .orElseThrow(() -> new IllegalArgumentException("Origin account not found"));
        BankAccount destinationAccount = bankAccountPort.findById(transfer.getDestinationAccount())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        // Approve transfer
        transfer.approve(approverUserId);

        // Execute movements
        originAccount.withdraw(transfer.getAmount());
        destinationAccount.deposit(transfer.getAmount());

        // Save
        bankAccountPort.save(originAccount);
        bankAccountPort.save(destinationAccount);
        transferPort.save(transfer);

        // Publish event
        eventPublisher.publish(new TransferApprovedEvent(transfer, approverUserId, approverRole.name()));
    }
}
