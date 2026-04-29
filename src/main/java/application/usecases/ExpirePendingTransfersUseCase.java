package application.usecases;

import domain.events.TransferExpiredEvent;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import domain.ports.events.EventPublisher;
import domain.ports.transfer.TransferPort;

import java.time.LocalDateTime;
import java.util.List;

public class ExpirePendingTransfersUseCase {

    private final TransferPort transferPort;
    private final EventPublisher eventPublisher;

    public ExpirePendingTransfersUseCase(TransferPort transferPort, EventPublisher eventPublisher) {
        this.transferPort = transferPort;
        this.eventPublisher = eventPublisher;
    }

    public void execute(int systemUserId) {
        // threshold 60 minutes ago
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(60);
        
        List<Transfer> expiredTransfers = transferPort.findPendingTransfersOlderThan(threshold);

        for (Transfer transfer : expiredTransfers) {
            transfer.expire();
            transferPort.save(transfer);
            eventPublisher.publish(new TransferExpiredEvent(transfer, systemUserId, UserRole.INTERNAL_ANALYST.name())); // Using INTERNAL_ANALYST as system role fallback
        }
    }
}
