package application.usecases;

import domain.events.TransferRejectedEvent;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;
import java.util.Map;

public class RejectTransferUseCase {

    private TransferPort transferPort;
    private EventPublisher eventPublisher;

    public void setTransferPort(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void execute(int transferId, int approverUserId, UserRole approverRole) {
        if (approverRole != UserRole.ENTERPRISE_SUPERVISOR) {
            throw new SecurityException("Only an Enterprise Supervisor can reject transfers");
        }

        Transfer transfer = transferPort.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        // Reject transfer
        transfer.reject(approverUserId);

        // Save
        transferPort.save(transfer);

        // Publish event
        TransferRejectedEvent event = new TransferRejectedEvent();
        event.setUserId(approverUserId);
        event.setUserRole(approverRole.name());
        event.setAffectedProductId(String.valueOf(transfer.getTransferId()));
        event.setEventData(Map.of("amount", transfer.getAmount(), "originAccount", transfer.getOriginAccount(), "destinationAccount", transfer.getDestinationAccount()));
        eventPublisher.publish(event);
    }
}
