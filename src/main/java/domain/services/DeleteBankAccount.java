package domain.services;


import domain.ports.account.BankAccountPort;

public class DeleteBankAccount {
    private BankAccountPort bankAccountPort;
    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void execute(String id) {
        bankAccountPort.delete(id);
    }
}





