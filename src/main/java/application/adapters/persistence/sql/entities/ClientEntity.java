package application.adapters.persistence.sql.entities;

import domain.models.client.ClientStatus;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String identification;

    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    public ClientEntity() {
    }

    public ClientEntity(Integer id, String fullName, String identification, ClientStatus clientStatus) {
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
        this.clientStatus = clientStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
