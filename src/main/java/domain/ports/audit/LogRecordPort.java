package domain.ports.audit;

import domain.models.audit.LogRecord;
import java.util.Optional;
import java.util.List;

public interface LogRecordPort {
    LogRecord save(LogRecord log);
    Optional<LogRecord> findById(String id);
    void delete(String id);
    List<LogRecord> findAll();
}

