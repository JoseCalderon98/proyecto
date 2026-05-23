package application.usecases;

import domain.models.client.IndividualClient;
import domain.models.client.CorporateClient;
import domain.services.CreateNaturalClient;
import domain.services.CreateCompanyClient;

public class ClientUseCase {

    private CreateNaturalClient createNaturalClient;
    private CreateCompanyClient createCompanyClient;

    public void setCreateNaturalClient(CreateNaturalClient createNaturalClient) {
        this.createNaturalClient = createNaturalClient;
    }

    public void setCreateCompanyClient(CreateCompanyClient createCompanyClient) {
        this.createCompanyClient = createCompanyClient;
    }

    public IndividualClient createIndividualClient(IndividualClient client) {
        return createNaturalClient.execute(client);
    }

    public CorporateClient createCorporateClient(CorporateClient client) {
        return createCompanyClient.execute(client);
    }
}
