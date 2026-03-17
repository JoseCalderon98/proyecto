package domain.audit;

import java.util.Date;

public class LogRecord {
    private String logId;
    private String operationType;
    private Date operationDateTime;
    private int userId;
    private String userRole;
    private String affectedProductId;
    private String detailData;

    public LogRecord(String logId, String operationType, Date operationDateTime, int userId, String userRole, String affectedProductId, String detailData) {
        this.logId = logId;
        this.operationType = operationType;
        this.operationDateTime = operationDateTime;
        this.userId = userId;
        this.userRole = userRole;
        this.affectedProductId = affectedProductId;
        this.detailData = detailData;
    }

    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public Date getOperationDateTime() { return operationDateTime; }
    public void setOperationDateTime(Date operationDateTime) { this.operationDateTime = operationDateTime; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getAffectedProductId() { return affectedProductId; }
    public void setAffectedProductId(String affectedProductId) { this.affectedProductId = affectedProductId; }

    public String getDetailData() { return detailData; }
    public void setDetailData(String detailData) { this.detailData = detailData; }
}
