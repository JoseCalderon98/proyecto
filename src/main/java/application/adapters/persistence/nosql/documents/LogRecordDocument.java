package application.adapters.persistence.nosql.documents;

import domain.models.audit.OperationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "audit_logs")
public class LogRecordDocument {
    
    @Id
    private String logId;
    
    private OperationType operationType;
    private LocalDateTime operationDateTime;
    private int userId;
    private String userRole;
    private String affectedProductId;
    private Map<String, Object> detailData;

    // Default constructor for Spring Data MongoDB
    public LogRecordDocument() {}

    // Getters and Setters
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }

    public OperationType getOperationType() { return operationType; }
    public void setOperationType(OperationType operationType) { this.operationType = operationType; }

    public LocalDateTime getOperationDateTime() { return operationDateTime; }
    public void setOperationDateTime(LocalDateTime operationDateTime) { this.operationDateTime = operationDateTime; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getAffectedProductId() { return affectedProductId; }
    public void setAffectedProductId(String affectedProductId) { this.affectedProductId = affectedProductId; }

    public Map<String, Object> getDetailData() { return detailData; }
    public void setDetailData(Map<String, Object> detailData) { this.detailData = detailData; }
}
