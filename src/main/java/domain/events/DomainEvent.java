package domain.events;

import java.time.LocalDateTime;
import java.util.Map;

public interface DomainEvent {
    String getEventId();
    LocalDateTime getOccurredOn();
    String getEventType();
    int getUserId();
    String getUserRole();
    String getAffectedProductId();
    Map<String, Object> getEventData();
}
