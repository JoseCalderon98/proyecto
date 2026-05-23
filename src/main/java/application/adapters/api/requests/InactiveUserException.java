package application.adapters.api.requests;

public class InactiveUserException extends IllegalStateException {
    public InactiveUserException(String message) {
        super(message);
    }
}
