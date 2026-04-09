package domain.models.audit;

import java.time.LocalDateTime;
import java.util.Map;

public class LogRecord {
    private String logId;
    private OperationType operationType;
    private LocalDateTime operationDateTime;
    private int userId;
    private String userRole; // Consider converting to Domain.user.UserRole if needed, but cross-dependency is fine for now, keeping as String or we can use UserRole enum. Let's use UserRole.
    private String affectedProductId;
    private Map<String, Object> detailData;

    public LogRecord(String logId, OperationType operationType, LocalDateTime operationDateTime, int userId, String userRole, String affectedProductId, Map<String, Object> detailData) {
        this.logId = logId;
        this.operationType = operationType;
        this.operationDateTime = operationDateTime;
        this.userId = userId;
        this.userRole = userRole;
        this.affectedProductId = affectedProductId;
        this.detailData = detailData;
    }

    public LogRecord() {
    }

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

