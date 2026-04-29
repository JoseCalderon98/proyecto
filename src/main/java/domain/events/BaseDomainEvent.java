package domain.events;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public abstract class BaseDomainEvent implements DomainEvent {
    private final String eventId;
    private final LocalDateTime occurredOn;
    private final String eventType;
    private final int userId;
    private final String userRole;
    private final String affectedProductId;
    private final Map<String, Object> eventData;

    public BaseDomainEvent(String eventType, int userId, String userRole, String affectedProductId, Map<String, Object> eventData) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = LocalDateTime.now();
        this.eventType = eventType;
        this.userId = userId;
        this.userRole = userRole;
        this.affectedProductId = affectedProductId;
        this.eventData = eventData;
    }

    @Override
    public String getEventId() { return eventId; }

    @Override
    public LocalDateTime getOccurredOn() { return occurredOn; }

    @Override
    public String getEventType() { return eventType; }

    @Override
    public int getUserId() { return userId; }

    @Override
    public String getUserRole() { return userRole; }

    @Override
    public String getAffectedProductId() { return affectedProductId; }

    @Override
    public Map<String, Object> getEventData() { return eventData; }
}
