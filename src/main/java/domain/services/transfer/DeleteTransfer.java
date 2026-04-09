package domain.services.transfer;

import domain.repositories.transfer.TransferRepository;

public class DeleteTransfer {
    private final TransferRepository repository;

    public DeleteTransfer(TransferRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        repository.delete(id);
    }
}

