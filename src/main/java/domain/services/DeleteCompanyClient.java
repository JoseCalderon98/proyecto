package domain.services;


import domain.ports.client.CorporateClientPort;

public class DeleteCompanyClient {
    private final CorporateClientPort Port;

    public DeleteCompanyClient(CorporateClientPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}




