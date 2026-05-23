package application.listeners;

import domain.events.DomainEvent;
import domain.models.audit.LogRecord;
import domain.models.audit.OperationType;
import domain.ports.audit.LogRecordPort;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditLogSubscriber {

    @Autowired
    private LogRecordPort logRecordPort;

    @Async
    @EventListener
    public void onDomainEvent(DomainEvent event) {
        OperationType opType = parseOperationType(event.getEventType());
        
        LogRecord log = new LogRecord();
        log.setLogId(event.getEventId());
        log.setOperationType(opType);
        log.setOperationDateTime(event.getOccurredOn());
        log.setUserId(event.getUserId());
        log.setUserRole(event.getUserRole());
        log.setAffectedProductId(event.getAffectedProductId());
        log.setDetailData(event.getEventData());
        
        logRecordPort.save(log);
    }

    private OperationType parseOperationType(String eventType) {
        switch (eventType) {
            case "LOAN_APPROVED": return OperationType.LOAN_APPROVAL;
            case "LOAN_DISBURSED": return OperationType.LOAN_DISBURSEMENT;
            case "TRANSFER_CREATED": return OperationType.TRANSFER;
            case "TRANSFER_APPROVED": return OperationType.TRANSFER_APPROVAL;
            case "TRANSFER_EXPIRED": return OperationType.TRANSFER_EXPIRATION;
            default: return OperationType.TRANSFER; // Fallback
        }
    }
}
