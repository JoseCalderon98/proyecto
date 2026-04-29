package domain.services;


import domain.ports.transfer.TransferPort;

public class DeleteTransfer {
    private final TransferPort Port;

    public DeleteTransfer(TransferPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}




