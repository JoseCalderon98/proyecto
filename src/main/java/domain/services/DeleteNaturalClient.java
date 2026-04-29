package domain.services;


import domain.ports.client.IndividualClientPort;

public class DeleteNaturalClient {
    private final IndividualClientPort Port;

    public DeleteNaturalClient(IndividualClientPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}




