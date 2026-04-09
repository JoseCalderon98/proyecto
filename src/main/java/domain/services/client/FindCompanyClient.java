package domain.services.client;

import domain.models.client.CorporateClient;
import domain.repositories.client.CorporateClientRepository;
import java.util.Optional;

public class FindCompanyClient {
    private final CorporateClientRepository repository;

    public FindCompanyClient(CorporateClientRepository repository) {
        this.repository = repository;
    }

    public Optional<CorporateClient> byId(int id) {
        return repository.findById(id);
    }
}

