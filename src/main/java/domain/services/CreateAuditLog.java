package domain.services;


import domain.models.audit.LogRecord;
import domain.ports.audit.LogRecordPort;

public class CreateAuditLog {
    private final LogRecordPort Port;

    public CreateAuditLog(LogRecordPort Port) {
        this.Port = Port;
    }

    public LogRecord execute(LogRecord log) {
        return Port.save(log);
    }
}




