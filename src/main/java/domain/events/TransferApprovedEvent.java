package domain.events;

import domain.models.transfer.Transfer;
import java.util.Map;

public class TransferApprovedEvent extends BaseDomainEvent {
    public TransferApprovedEvent(Transfer transfer, int approverUserId, String approverRole) {
        super(
            "TRANSFER_APPROVED",
            approverUserId,
            approverRole,
            String.valueOf(transfer.getTransferId()),
            Map.of(
                "amount", transfer.getAmount(),
                "originAccount", transfer.getOriginAccount(),
                "destinationAccount", transfer.getDestinationAccount()
            )
        );
    }
}
