package domain.repositories.account;

import domain.models.account.BankAccount;
import java.util.Optional;
import java.util.List;

public interface BankAccountRepository {
    BankAccount save(BankAccount account);
    Optional<BankAccount> findById(int id);
    void delete(int id);
    List<BankAccount> findAll();
}
