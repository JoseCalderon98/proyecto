package domain.services.transfer;

import domain.models.transfer.Transfer;
import domain.repositories.transfer.TransferRepository;
import java.util.Optional;

public class FindTransfer {
    private final TransferRepository repository;

    public FindTransfer(TransferRepository repository) {
        this.repository = repository;
    }

    public Optional<Transfer> byId(int id) {
        return repository.findById(id);
    }
}

