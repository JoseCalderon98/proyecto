package domain.services;


import domain.models.transfer.Transfer;
import domain.ports.transfer.TransferPort;

public class CreateTransfer {
    private TransferPort Port;
    public void setPort(TransferPort Port) {
        this.Port = Port;
    }

    public Transfer execute(Transfer transfer) {
        return Port.save(transfer);
    }
}





