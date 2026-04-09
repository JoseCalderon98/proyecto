package domain.services.account;

import domain.models.account.BankAccount;
import domain.repositories.account.BankAccountRepository;
import java.util.Optional;

public class FindBankAccount {
    private final BankAccountRepository bankAccountRepository;

    public FindBankAccount(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Optional<BankAccount> byId(int id) {
        return bankAccountRepository.findById(id);
    }
}

