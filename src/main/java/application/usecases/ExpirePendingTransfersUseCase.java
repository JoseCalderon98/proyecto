package application.usecases;

import domain.events.TransferExpiredEvent;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ExpirePendingTransfersUseCase {

    private TransferPort transferPort;
    private EventPublisher eventPublisher;

    public void setTransferPort(TransferPort transferPort) {
        this.transferPort = transferPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void execute(int systemUserId) {
        // threshold 60 minutes ago
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(60);
        
        List<Transfer> expiredTransfers = transferPort.findPendingTransfersOlderThan(threshold);

        for (Transfer transfer : expiredTransfers) {
            transfer.expire();
            transferPort.save(transfer);

            // Publish event
            TransferExpiredEvent event = new TransferExpiredEvent();
            event.setUserId(systemUserId);
            event.setUserRole(UserRole.INTERNAL_ANALYST.name());
            event.setAffectedProductId(String.valueOf(transfer.getTransferId()));
            event.setEventData(Map.of(
                "amount", transfer.getAmount(),
                "originAccount", transfer.getOriginAccount(),
                "destinationAccount", transfer.getDestinationAccount()
            ));
            eventPublisher.publish(event);
        }
    }
}
