package domain.client;

public abstract class Client {
    private String fullName;
    private String identification;
    private ClientStatus clientStatus;

    public Client(String fullName, String identification, ClientStatus clientStatus) {
        this.fullName = fullName;
        this.identification = identification;
        this.clientStatus = clientStatus;
    }

    public Client() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
}
