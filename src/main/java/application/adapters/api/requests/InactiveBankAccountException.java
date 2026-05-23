package application.adapters.api.requests;

public class InactiveBankAccountException extends IllegalStateException {
    public InactiveBankAccountException(String message) {
        super(message);
    }
}
