package domain.services;


import domain.models.client.IndividualClient;
import domain.ports.client.IndividualClientPort;
import java.util.Optional;

public class FindNaturalClient {
    private IndividualClientPort Port;
    public void setPort(IndividualClientPort Port) {
        this.Port = Port;
    }

    public Optional<IndividualClient> byId(int id) {
        return Port.findById(id);
    }
}





