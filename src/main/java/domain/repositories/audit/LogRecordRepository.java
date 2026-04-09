package domain.repositories.audit;

import domain.models.audit.LogRecord;
import java.util.Optional;
import java.util.List;

public interface LogRecordRepository {
    LogRecord save(LogRecord log);
    Optional<LogRecord> findById(int id);
    void delete(int id);
    List<LogRecord> findAll();
}
