package domain.services;


import domain.models.transfer.Transfer;
import domain.ports.transfer.TransferPort;

public class CreateTransfer {
    private final TransferPort Port;

    public CreateTransfer(TransferPort Port) {
        this.Port = Port;
    }

    public Transfer execute(Transfer transfer) {
        return Port.save(transfer);
    }
}




