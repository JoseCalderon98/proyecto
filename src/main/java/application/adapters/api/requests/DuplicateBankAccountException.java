package application.adapters.api.requests;

public class DuplicateBankAccountException extends IllegalArgumentException {
    public DuplicateBankAccountException(String message) {
        super(message);
    }
}
