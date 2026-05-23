package application.adapters.api.requests;

public class UnauthorizedRoleException extends SecurityException {
    public UnauthorizedRoleException(String message) {
        super(message);
    }
}
