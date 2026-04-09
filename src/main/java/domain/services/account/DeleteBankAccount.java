package domain.services.account;

import domain.repositories.account.BankAccountRepository;

public class DeleteBankAccount {
    private final BankAccountRepository bankAccountRepository;

    public DeleteBankAccount(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void execute(int id) {
        bankAccountRepository.delete(id);
    }
}

