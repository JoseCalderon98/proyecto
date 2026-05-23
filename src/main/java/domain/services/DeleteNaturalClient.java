package domain.services;


import domain.ports.client.IndividualClientPort;

public class DeleteNaturalClient {
    private IndividualClientPort Port;
    public void setPort(IndividualClientPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}





