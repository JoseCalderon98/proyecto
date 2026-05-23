package domain.services;


import domain.ports.client.CorporateClientPort;

public class DeleteCompanyClient {
    private CorporateClientPort Port;
    public void setPort(CorporateClientPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}





