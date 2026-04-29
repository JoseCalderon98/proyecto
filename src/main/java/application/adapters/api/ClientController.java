package application.adapters.api;

import domain.models.client.IndividualClient;
import application.usecases.ClientUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientUseCase clientUseCase;

    public ClientController(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @PostMapping("/individual")
    public ResponseEntity<IndividualClient> createIndividualClient(@RequestBody IndividualClient client) {
        IndividualClient createdClient = clientUseCase.createIndividualClient(client);
        return ResponseEntity.ok(createdClient);
    }
}
