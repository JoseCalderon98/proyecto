package domain.services.account;

import domain.models.account.BankAccount;
import domain.repositories.account.BankAccountRepository;

public class CreateBankAccount {
    private final BankAccountRepository bankAccountRepository;

    public CreateBankAccount(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount execute(BankAccount account) {
        return bankAccountRepository.save(account);
    }
}

