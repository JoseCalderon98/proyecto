package domain.services;


import domain.models.audit.LogRecord;
import domain.ports.audit.LogRecordPort;
import java.util.Optional;

public class FindAuditLog {
    private LogRecordPort Port;
    public void setPort(LogRecordPort Port) {
        this.Port = Port;
    }

    public Optional<LogRecord> byId(String id) {
        return Port.findById(id);
    }
}





