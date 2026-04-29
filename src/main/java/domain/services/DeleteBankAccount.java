package domain.services;


import domain.ports.account.BankAccountPort;

public class DeleteBankAccount {
    private final BankAccountPort bankAccountPort;

    public DeleteBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void execute(String id) {
        bankAccountPort.delete(id);
    }
}




