package application.adapters.api.requests;

public class InactiveClientException extends IllegalStateException {
    public InactiveClientException(String message) {
        super(message);
    }
}
