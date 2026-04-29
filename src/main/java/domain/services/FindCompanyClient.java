package domain.services;


import domain.models.client.CorporateClient;
import domain.ports.client.CorporateClientPort;
import java.util.Optional;

public class FindCompanyClient {
    private final CorporateClientPort Port;

    public FindCompanyClient(CorporateClientPort Port) {
        this.Port = Port;
    }

    public Optional<CorporateClient> byId(int id) {
        return Port.findById(id);
    }
}




