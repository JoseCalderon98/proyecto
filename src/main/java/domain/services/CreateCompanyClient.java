package domain.services;

import domain.models.client.CorporateClient;
import domain.ports.client.CorporateClientPort;

public class CreateCompanyClient {

    private CorporateClientPort Port;
    public void setPort(CorporateClientPort Port) {
        this.Port = Port;
    }

    public CorporateClient execute(CorporateClient client) {
        client.validate();
        
        for (CorporateClient existing : Port.findAll()) {
            if (existing.getIdentification().equals(client.getIdentification())) {
                throw new IllegalArgumentException("Identification already exists");
            }
        }

        return Port.save(client);
    }
}
