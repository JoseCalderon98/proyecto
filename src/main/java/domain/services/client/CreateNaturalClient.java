package domain.services.client;

import domain.models.client.IndividualClient;
import domain.repositories.client.IndividualClientRepository;

public class CreateNaturalClient {
    private final IndividualClientRepository repository;

    public CreateNaturalClient(IndividualClientRepository repository) {
        this.repository = repository;
    }

    public IndividualClient execute(IndividualClient client) {
        return repository.save(client);
    }
}

