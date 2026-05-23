package application.adapters.persistence.nosql.repositories;

import application.adapters.persistence.nosql.documents.LogRecordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoLogRecordRepository extends MongoRepository<LogRecordDocument, String> {
}
