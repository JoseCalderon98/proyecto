package domain.repositories.client;

import domain.models.client.IndividualClient;
import java.util.Optional;
import java.util.List;

public interface IndividualClientRepository {
    IndividualClient save(IndividualClient client);
    Optional<IndividualClient> findById(int id);
    void delete(int id);
    List<IndividualClient> findAll();
}
