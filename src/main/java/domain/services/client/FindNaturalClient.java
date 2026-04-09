package domain.services.client;

import domain.models.client.IndividualClient;
import domain.repositories.client.IndividualClientRepository;
import java.util.Optional;

public class FindNaturalClient {
    private final IndividualClientRepository repository;

    public FindNaturalClient(IndividualClientRepository repository) {
        this.repository = repository;
    }

    public Optional<IndividualClient> byId(int id) {
        return repository.findById(id);
    }
}

