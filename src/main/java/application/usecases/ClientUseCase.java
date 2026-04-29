package application.usecases;

import domain.models.client.IndividualClient;
import domain.services.CreateNaturalClient;

public class ClientUseCase {

    private final CreateNaturalClient createNaturalClient;

    public ClientUseCase(CreateNaturalClient createNaturalClient) {
        this.createNaturalClient = createNaturalClient;
    }

    public IndividualClient createIndividualClient(IndividualClient client) {
        return createNaturalClient.execute(client);
    }
}
