package application.adapters.api;

import domain.models.account.BankAccount;
import domain.models.account.AccountType;
import domain.models.account.Currency;
import application.adapters.api.requests.CreateBankAccountRequest;
import application.usecases.CreateBankAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    @Autowired
    private CreateBankAccountUseCase createBankAccountUseCase;

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@Valid @RequestBody CreateBankAccountRequest request) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(AccountType.valueOf(request.getAccountType()));
        account.setHolderId(request.getHolderId());
        account.setCurrentBalance(request.getInitialBalance());
        account.setCurrency(Currency.valueOf(request.getCurrency()));
        if (request.getOverdraftLimit() != null) {
            account.setOverdraftLimit(request.getOverdraftLimit());
        }

        BankAccount createdAccount = createBankAccountUseCase.execute(account);
        return ResponseEntity.ok(createdAccount);
    }
}
