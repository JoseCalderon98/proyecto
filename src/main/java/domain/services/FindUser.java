package domain.services;


import domain.models.user.User;
import domain.ports.user.UserPort;
import java.util.Optional;

public class FindUser {
    private UserPort userPort;
    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public Optional<User> byId(int id) {
        return userPort.findById(id);
    }
}





