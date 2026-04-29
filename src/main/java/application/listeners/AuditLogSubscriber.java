package application.listeners;

import domain.events.DomainEvent;
import domain.models.audit.LogRecord;
import domain.models.audit.OperationType;
import domain.ports.audit.LogRecordPort;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditLogSubscriber {

    private final LogRecordPort logRecordPort;

    public AuditLogSubscriber(LogRecordPort logRecordPort) {
        this.logRecordPort = logRecordPort;
    }

    @Async
    @EventListener
    public void onDomainEvent(DomainEvent event) {
        OperationType opType = parseOperationType(event.getEventType());
        
        LogRecord log = new LogRecord(
            event.getEventId(),
            opType,
            event.getOccurredOn(),
            event.getUserId(),
            event.getUserRole(),
            event.getAffectedProductId(),
            event.getEventData()
        );
        
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
