package domain.events;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class LoanDisbursedEvent implements DomainEvent {
    private String eventId = UUID.randomUUID().toString();
    private LocalDateTime occurredOn = LocalDateTime.now();
    private String eventType = "LOAN_DISBURSED";
    private int userId;
    private String userRole;
    private String affectedProductId;
    private Map<String, Object> eventData;

    @Override
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    @Override
    public LocalDateTime getOccurredOn() { return occurredOn; }
    public void setOccurredOn(LocalDateTime occurredOn) { this.occurredOn = occurredOn; }

    @Override
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    @Override
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    @Override
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    @Override
    public String getAffectedProductId() { return affectedProductId; }
    public void setAffectedProductId(String affectedProductId) { this.affectedProductId = affectedProductId; }

    @Override
    public Map<String, Object> getEventData() { return eventData; }
    public void setEventData(Map<String, Object> eventData) { this.eventData = eventData; }
}
