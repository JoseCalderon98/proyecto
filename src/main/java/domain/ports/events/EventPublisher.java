package domain.ports.events;

import domain.events.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}
