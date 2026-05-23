package application.adapters.persistence.nosql;

import application.adapters.persistence.nosql.documents.LogRecordDocument;
import application.adapters.persistence.nosql.repositories.MongoLogRecordRepository;
import domain.models.audit.LogRecord;
import domain.ports.audit.LogRecordPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LogRecordPersistenceAdapter implements LogRecordPort {

    @Autowired
    private MongoLogRecordRepository logRecordRepository;

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
        LogRecord log = new LogRecord();
        log.setLogId(doc.getLogId());
        log.setOperationType(doc.getOperationType());
        log.setOperationDateTime(doc.getOperationDateTime());
        log.setUserId(doc.getUserId());
        log.setUserRole(doc.getUserRole());
        log.setAffectedProductId(doc.getAffectedProductId());
        log.setDetailData(doc.getDetailData());
        return log;
    }
}
