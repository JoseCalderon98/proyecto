package domain.services;


import domain.models.user.User;
import domain.ports.user.UserPort;

public class CreateUser {
    private final UserPort userPort;

    public CreateUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public User execute(User user) {
        // Here you would add business rules, validation, etc.
        return userPort.save(user);
    }
}




