package application.adapters.api.requests;

public class BankAccountNotFoundException extends IllegalArgumentException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
