package application.adapters.api;

import domain.models.client.IndividualClient;
import domain.models.client.CorporateClient;
import domain.models.client.ClientStatus;
import application.adapters.api.requests.CreateIndividualClientRequest;
import application.adapters.api.requests.CreateCorporateClientRequest;
import application.usecases.ClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientUseCase clientUseCase;

    @PostMapping("/individual")
    public ResponseEntity<IndividualClient> createIndividualClient(@Valid @RequestBody CreateIndividualClientRequest request) {
        IndividualClient client = new IndividualClient();
        client.setFullName(request.getName());
        client.setIdentification(request.getIdentificationNumber());
        client.setBirthDate(request.getBirthDate());
        client.setClientStatus(ClientStatus.ACTIVE);
        
        IndividualClient createdClient = clientUseCase.createIndividualClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @PostMapping("/corporate")
    public ResponseEntity<CorporateClient> createCorporateClient(@Valid @RequestBody CreateCorporateClientRequest request) {
        CorporateClient client = new CorporateClient();
        client.setFullName(request.getFullName());
        client.setIdentification(request.getIdentification());
        client.setCompanyName(request.getCompanyName());
        client.setTaxId(request.getTaxId());
        client.setLegalRepresentativeId(request.getLegalRepresentativeId());
        client.setClientStatus(ClientStatus.ACTIVE);

        CorporateClient createdClient = clientUseCase.createCorporateClient(client);
        return ResponseEntity.ok(createdClient);
    }
}
