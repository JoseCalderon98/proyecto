package domain.services;


import domain.models.client.CorporateClient;
import domain.ports.client.CorporateClientPort;

public class CreateCompanyClient {
    private final CorporateClientPort Port;

    public CreateCompanyClient(CorporateClientPort Port) {
        this.Port = Port;
    }

    public CorporateClient execute(CorporateClient client) {
        return Port.save(client);
    }
}




