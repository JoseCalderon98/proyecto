package domain.services.audit;

import domain.models.audit.LogRecord;
import domain.repositories.audit.LogRecordRepository;

public class CreateAuditLog {
    private final LogRecordRepository repository;

    public CreateAuditLog(LogRecordRepository repository) {
        this.repository = repository;
    }

    public LogRecord execute(LogRecord log) {
        return repository.save(log);
    }
}

