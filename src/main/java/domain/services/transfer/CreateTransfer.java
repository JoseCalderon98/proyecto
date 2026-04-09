package domain.services.transfer;

import domain.models.transfer.Transfer;
import domain.repositories.transfer.TransferRepository;

public class CreateTransfer {
    private final TransferRepository repository;

    public CreateTransfer(TransferRepository repository) {
        this.repository = repository;
    }

    public Transfer execute(Transfer transfer) {
        return repository.save(transfer);
    }
}

