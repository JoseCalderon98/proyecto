package application.usecases;

import domain.models.transfer.Transfer;
import domain.models.transfer.TransferStatus;
import domain.ports.transfer.TransferPort;
import domain.ports.audit.LogRecordPort;
import domain.models.audit.LogRecord;
import domain.models.audit.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpireTransfersUseCase {

    @Autowired
    private TransferPort transferPort;

    @Autowired
    private LogRecordPort logRecordPort;

    @Transactional
    public void expirePendingTransfers() {
        LocalDateTime sixtyMinutesAgo = LocalDateTime.now().minusMinutes(60);
        List<Transfer> expiredTransfers = transferPort.findPendingTransfersOlderThan(sixtyMinutesAgo);

        for (Transfer transfer : expiredTransfers) {
            if (transfer.getTransferStatus() == TransferStatus.PENDING_APPROVAL) {
                // Rule 7.7: Update to EXPIRED
                transfer.expire();
                transferPort.save(transfer);

                // Rule 7.8: Audit trail
                LogRecord log = new LogRecord();
                log.setLogId(UUID.randomUUID().toString());
                log.setOperationType(OperationType.TRANSFER_EXPIRATION);
                log.setOperationDateTime(LocalDateTime.now());
                log.setUserId(0); // System User ID
                log.setUserRole("SYSTEM_CRON");
                log.setAffectedProductId(String.valueOf(transfer.getTransferId()));
                
                Map<String, Object> details = new HashMap<>();
                details.put("reason", "Automatic expiration after 60 minutes");
                details.put("transferId", transfer.getTransferId());
                details.put("amount", transfer.getAmount());
                log.setDetailData(details);

                logRecordPort.save(log);
            }
        }
    }
}
