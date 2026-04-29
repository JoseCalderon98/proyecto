package domain.events;

import domain.models.transfer.Transfer;
import java.util.Map;

public class TransferExpiredEvent extends BaseDomainEvent {
    public TransferExpiredEvent(Transfer transfer, int systemUserId, String systemRole) {
        super(
            "TRANSFER_EXPIRED",
            systemUserId,
            systemRole,
            String.valueOf(transfer.getTransferId()),
            Map.of(
                "amount", transfer.getAmount(),
                "originAccount", transfer.getOriginAccount(),
                "destinationAccount", transfer.getDestinationAccount()
            )
        );
    }
}
