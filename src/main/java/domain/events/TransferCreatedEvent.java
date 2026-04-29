package domain.events;

import domain.models.transfer.Transfer;
import java.util.Map;

public class TransferCreatedEvent extends BaseDomainEvent {
    public TransferCreatedEvent(Transfer transfer, int creatorUserId, String creatorRole) {
        super(
            "TRANSFER_CREATED",
            creatorUserId,
            creatorRole,
            String.valueOf(transfer.getTransferId()),
            Map.of(
                "amount", transfer.getAmount(),
                "originAccount", transfer.getOriginAccount(),
                "destinationAccount", transfer.getDestinationAccount(),
                "status", transfer.getTransferStatus().name()
            )
        );
    }
}
