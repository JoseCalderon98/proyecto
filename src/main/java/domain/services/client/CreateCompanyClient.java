package domain.services.client;

import domain.models.client.CorporateClient;
import domain.repositories.client.CorporateClientRepository;

public class CreateCompanyClient {
    private final CorporateClientRepository repository;

    public CreateCompanyClient(CorporateClientRepository repository) {
        this.repository = repository;
    }

    public CorporateClient execute(CorporateClient client) {
        return repository.save(client);
    }
}

