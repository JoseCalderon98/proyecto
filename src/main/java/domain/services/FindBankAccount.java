package domain.services;


import domain.models.account.BankAccount;
import domain.ports.account.BankAccountPort;
import java.util.Optional;

public class FindBankAccount {
    private final BankAccountPort bankAccountPort;

    public FindBankAccount(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public Optional<BankAccount> byId(String id) {
        return bankAccountPort.findById(id);
    }
}




