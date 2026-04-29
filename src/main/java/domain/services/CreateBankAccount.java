package domain.services;


import domain.models.account.BankAccount;
import domain.ports.account.BankAccountPort;

public class CreateBankAccount {
    private final BankAccountPort bankAccountPort;

    public CreateBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public BankAccount execute(BankAccount account) {
        return bankAccountPort.save(account);
    }
}




