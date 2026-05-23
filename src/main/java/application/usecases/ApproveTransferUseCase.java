package application.usecases;

import domain.events.TransferApprovedEvent;
import domain.models.account.BankAccount;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;
import application.adapters.api.requests.BankAccountNotFoundException;

public class ApproveTransferUseCase {

    private TransferPort transferPort;
    private BankAccountPort bankAccountPort;
    private EventPublisher eventPublisher;
    public void setTransferPort(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }



    public void execute(int transferId, int approverUserId, UserRole approverRole) {
        if (approverRole != UserRole.ENTERPRISE_SUPERVISOR) {
            throw new SecurityException("Only an Enterprise Supervisor can approve transfers");
        }

        Transfer transfer = transferPort.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        BankAccount originAccount = bankAccountPort.findById(transfer.getOriginAccount())
                .orElseThrow(() -> new BankAccountNotFoundException("Origin account not found: " + transfer.getOriginAccount()));
        BankAccount destinationAccount = bankAccountPort.findById(transfer.getDestinationAccount())
                .orElseThrow(() -> new BankAccountNotFoundException("Destination account not found: " + transfer.getDestinationAccount()));

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
        TransferApprovedEvent event = new TransferApprovedEvent();
        event.setUserId(approverUserId);
        event.setUserRole(approverRole.name());
        event.setAffectedProductId(String.valueOf(transfer.getTransferId()));
        event.setEventData(java.util.Map.of("amount", transfer.getAmount(), "originAccount", transfer.getOriginAccount(), "destinationAccount", transfer.getDestinationAccount()));
        eventPublisher.publish(event);
    }
}


