package domain.services.client;

import domain.repositories.client.IndividualClientRepository;

public class DeleteNaturalClient {
    private final IndividualClientRepository repository;

    public DeleteNaturalClient(IndividualClientRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        repository.delete(id);
    }
}

