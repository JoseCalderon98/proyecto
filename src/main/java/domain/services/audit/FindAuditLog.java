package domain.services.audit;

import domain.models.audit.LogRecord;
import domain.repositories.audit.LogRecordRepository;
import java.util.Optional;

public class FindAuditLog {
    private final LogRecordRepository repository;

    public FindAuditLog(LogRecordRepository repository) {
        this.repository = repository;
    }

    public Optional<LogRecord> byId(int id) {
        return repository.findById(id);
    }
}

