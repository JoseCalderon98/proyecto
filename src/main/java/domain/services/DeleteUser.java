package domain.services;


import domain.ports.user.UserPort;

public class DeleteUser {
    private final UserPort userPort;

    public DeleteUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public void execute(int id) {
        userPort.delete(id);
    }
}




