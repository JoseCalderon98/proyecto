package domain.services;


import domain.models.transfer.Transfer;
import domain.ports.transfer.TransferPort;
import java.util.Optional;

public class FindTransfer {
    private TransferPort Port;
    public void setPort(TransferPort Port) {
        this.Port = Port;
    }

    public Optional<Transfer> byId(int id) {
        return Port.findById(id);
    }
}





