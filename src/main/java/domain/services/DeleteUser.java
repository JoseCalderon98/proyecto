package domain.services;


import domain.ports.user.UserPort;

public class DeleteUser {
    private UserPort userPort;
    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public void execute(int id) {
        userPort.delete(id);
    }
}





