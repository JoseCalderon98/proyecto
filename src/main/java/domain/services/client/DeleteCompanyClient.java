package domain.services.client;

import domain.repositories.client.CorporateClientRepository;

public class DeleteCompanyClient {
    private final CorporateClientRepository repository;

    public DeleteCompanyClient(CorporateClientRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        repository.delete(id);
    }
}

