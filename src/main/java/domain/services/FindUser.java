package domain.services;


import domain.models.user.User;
import domain.ports.user.UserPort;
import java.util.Optional;

public class FindUser {
    private final UserPort userPort;

    public FindUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public Optional<User> byId(int id) {
        return userPort.findById(id);
    }
}




