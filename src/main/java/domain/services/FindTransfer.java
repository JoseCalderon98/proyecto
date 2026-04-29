package domain.services;


import domain.models.transfer.Transfer;
import domain.ports.transfer.TransferPort;
import java.util.Optional;

public class FindTransfer {
    private final TransferPort Port;

    public FindTransfer(TransferPort Port) {
        this.Port = Port;
    }

    public Optional<Transfer> byId(int id) {
        return Port.findById(id);
    }
}




