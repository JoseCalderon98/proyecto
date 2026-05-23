package domain.services;


import domain.ports.transfer.TransferPort;

public class DeleteTransfer {
    private TransferPort Port;
    public void setPort(TransferPort Port) {
        this.Port = Port;
    }

    public void execute(int id) {
        Port.delete(id);
    }
}





