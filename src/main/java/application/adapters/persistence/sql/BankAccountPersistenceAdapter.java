package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.BankAccountEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaBankAccountRepository;
import domain.models.account.BankAccount;
import domain.ports.account.BankAccountPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BankAccountPersistenceAdapter implements BankAccountPort {

    @Autowired
    private SpringDataJpaBankAccountRepository repository;

    @Override
    public BankAccount save(BankAccount account) {
        BankAccountEntity entity = new BankAccountEntity();
        entity.setAccountNumber(account.getAccountNumber());
        entity.setAccountType(account.getAccountType());
        entity.setHolderId(account.getHolderId());
        entity.setCurrentBalance(account.getCurrentBalance());
        entity.setCurrency(account.getCurrency());
        entity.setAccountStatus(account.getAccountStatus());
        entity.setOpeningDate(account.getOpeningDate());
        entity.setOverdraftLimit(account.getOverdraftLimit());

        repository.save(entity);
        return account;
    }

    @Override
    public Optional<BankAccount> findById(String accountNumber) {
        return repository.findById(accountNumber).map(entity -> {
            BankAccount account = new BankAccount();
            account.setAccountNumber(entity.getAccountNumber());
            account.setAccountType(entity.getAccountType());
            account.setHolderId(entity.getHolderId());
            account.setCurrentBalance(entity.getCurrentBalance());
            account.setCurrency(entity.getCurrency());
            account.setAccountStatus(entity.getAccountStatus());
            account.setOpeningDate(entity.getOpeningDate());
            account.setOverdraftLimit(entity.getOverdraftLimit());
            return account;
        });
    }

    @Override
    public void delete(String accountNumber) {
        repository.deleteById(accountNumber);
    }

    @Override
    public List<BankAccount> findAll() {
        return repository.findAll().stream().map(entity -> {
            BankAccount account = new BankAccount();
            account.setAccountNumber(entity.getAccountNumber());
            account.setAccountType(entity.getAccountType());
            account.setHolderId(entity.getHolderId());
            account.setCurrentBalance(entity.getCurrentBalance());
            account.setCurrency(entity.getCurrency());
            account.setAccountStatus(entity.getAccountStatus());
            account.setOpeningDate(entity.getOpeningDate());
            account.setOverdraftLimit(entity.getOverdraftLimit());
            return account;
        }).collect(Collectors.toList());
    }
}
