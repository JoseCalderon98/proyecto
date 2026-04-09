package domain.repositories.client;

import domain.models.client.CorporateClient;
import java.util.Optional;
import java.util.List;

public interface CorporateClientRepository {
    CorporateClient save(CorporateClient client);
    Optional<CorporateClient> findById(int id);
    void delete(int id);
    List<CorporateClient> findAll();
}
