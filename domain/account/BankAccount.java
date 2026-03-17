package domain.account;

import java.util.Date;

public class BankAccount {
    private String accountNumber;
    private String accountType;
    private String holderId;
    private double currentBalance;
    private String currency;
    private String accountStatus;
    private Date openingDate;

    public BankAccount(String accountNumber, String accountType, String holderId, double currentBalance, String currency, String accountStatus, Date openingDate) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.holderId = holderId;
        this.currentBalance = currentBalance;
        this.currency = currency;
        this.accountStatus = accountStatus;
        this.openingDate = openingDate;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getHolderId() { return holderId; }
    public void setHolderId(String holderId) { this.holderId = holderId; }

    public double getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(double currentBalance) { this.currentBalance = currentBalance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }

    public Date getOpeningDate() { return openingDate; }
    public void setOpeningDate(Date openingDate) { this.openingDate = openingDate; }
}
