package domain.models.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccount {
    private String accountNumber;
    private AccountType accountType;
    private String holderId;
    private BigDecimal currentBalance;
    private Currency currency;
    private AccountStatus accountStatus;
    private LocalDateTime openingDate;
    private BigDecimal overdraftLimit = BigDecimal.ZERO;


    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        if (this.accountStatus != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Cannot deposit into an inactive or blocked account");
        }
        this.currentBalance = this.currentBalance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (this.accountStatus != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Cannot withdraw from an inactive or blocked account");
        }
        BigDecimal availableFunds = this.currentBalance.add(this.overdraftLimit != null ? this.overdraftLimit : BigDecimal.ZERO);
        if (availableFunds.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds (including authorized overdraft)");
        }
        this.currentBalance = this.currentBalance.subtract(amount);
    }

    public void block() {
        this.accountStatus = AccountStatus.BLOCKED;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public String getHolderId() { return holderId; }
    public void setHolderId(String holderId) { this.holderId = holderId; }

    public BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(BigDecimal currentBalance) { this.currentBalance = currentBalance; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public AccountStatus getAccountStatus() { return accountStatus; }
    public void setAccountStatus(AccountStatus accountStatus) { this.accountStatus = accountStatus; }

    public LocalDateTime getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDateTime openingDate) { this.openingDate = openingDate; }

    public BigDecimal getOverdraftLimit() { return overdraftLimit; }
    public void setOverdraftLimit(BigDecimal overdraftLimit) { this.overdraftLimit = overdraftLimit != null ? overdraftLimit : BigDecimal.ZERO; }
}

