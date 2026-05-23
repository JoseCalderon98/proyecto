package application.adapters.api.requests;

public class UnauthorizedOwnerException extends IllegalArgumentException {
    public UnauthorizedOwnerException(String message) {
        super(message);
    }
}
