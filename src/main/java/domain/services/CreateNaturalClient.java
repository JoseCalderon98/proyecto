package domain.services;


import domain.models.client.IndividualClient;
import domain.ports.client.IndividualClientPort;


public class CreateNaturalClient {
    private IndividualClientPort Port;
    public void setPort(IndividualClientPort Port) {
        this.Port = Port;
    }

    public IndividualClient execute(IndividualClient client) {
        client.validate();
        
        for (IndividualClient existing : Port.findAll()) {
            if (existing.getIdentification().equals(client.getIdentification())) {
                throw new IllegalArgumentException("Identification already exists");
            }
        }

        return Port.save(client);
    }
}
