package application.adapters.persistence.nosql;

import application.adapters.persistence.nosql.documents.LogRecordDocument;
import application.adapters.persistence.nosql.repositories.MongoLogRecordRepository;
import domain.models.audit.LogRecord;
import domain.ports.audit.LogRecordPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LogRecordPersistenceAdapter implements LogRecordPort {

    private final MongoLogRecordRepository logRecordRepository;

    public LogRecordPersistenceAdapter(MongoLogRecordRepository logRecordRepository) {
        this.logRecordRepository = logRecordRepository;
    }

    @Override
    public LogRecord save(LogRecord log) {
        LogRecordDocument document = toDocument(log);
        LogRecordDocument savedDocument = logRecordRepository.save(document);
        return toDomain(savedDocument);
    }

    @Override
    public Optional<LogRecord> findById(String id) {
        return logRecordRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(String id) {
        logRecordRepository.deleteById(id);
    }

    @Override
    public List<LogRecord> findAll() {
        return logRecordRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private LogRecordDocument toDocument(LogRecord log) {
        LogRecordDocument doc = new LogRecordDocument();
        doc.setLogId(log.getLogId());
        doc.setOperationType(log.getOperationType());
        doc.setOperationDateTime(log.getOperationDateTime());
        doc.setUserId(log.getUserId());
        doc.setUserRole(log.getUserRole());
        doc.setAffectedProductId(log.getAffectedProductId());
        doc.setDetailData(log.getDetailData());
        return doc;
    }

    private LogRecord toDomain(LogRecordDocument doc) {
        return new LogRecord(
                doc.getLogId(),
                doc.getOperationType(),
                doc.getOperationDateTime(),
                doc.getUserId(),
                doc.getUserRole(),
                doc.getAffectedProductId(),
                doc.getDetailData()
        );
    }
}
