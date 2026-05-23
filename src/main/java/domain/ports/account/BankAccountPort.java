package domain.ports.account;

import domain.models.account.BankAccount;
import java.util.Optional;
import java.util.List;

public interface BankAccountPort {
    BankAccount save(BankAccount account);
    Optional<BankAccount> findById(String accountNumber);
    void delete(String accountNumber);
    List<BankAccount> findAll();
}

