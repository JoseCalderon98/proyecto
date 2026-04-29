package application.adapters.persistence.sql.entities;

import domain.models.client.ClientStatus;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class IndividualClientEntity extends ClientEntity {
    private LocalDate birthDate;

    public IndividualClientEntity() {
    }

    public IndividualClientEntity(Integer id, String fullName, String identification, ClientStatus clientStatus, LocalDate birthDate) {
        super(id, fullName, identification, clientStatus);
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
