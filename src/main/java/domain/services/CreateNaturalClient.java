package domain.services;


import domain.models.client.IndividualClient;
import domain.ports.client.IndividualClientPort;


public class CreateNaturalClient {
    private final IndividualClientPort Port;

    public CreateNaturalClient(IndividualClientPort Port) {
        this.Port = Port;
    }

    public IndividualClient execute(IndividualClient client) {
        return Port.save(client);
    }
}




