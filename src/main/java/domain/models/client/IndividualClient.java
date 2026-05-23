package domain.models.client;

import java.time.LocalDate;

public class IndividualClient {
    private Integer id;
    private String fullName;
    private String identification;
    private ClientStatus clientStatus;
    private LocalDate birthDate;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Getters / setters delegados para que cumpla con la especificación de nombre si es necesario
    public String getName() {
        return getFullName();
    }

    public void setName(String name) {
        setFullName(name);
    }

    public String getIdentificationNumber() {
        return getIdentification();
    }

    public void setIdentificationNumber(String identificationNumber) {
        setIdentification(identificationNumber);
    }

    public void validate() {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is mandatory");
        }
        if (clientStatus == null) {
            throw new IllegalArgumentException("Client status is mandatory");
        }
        if (birthDate != null) {
            if (java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears() < 18) {
                throw new IllegalArgumentException("Individual client must be at least 18 years old");
            }
        } else {
            throw new IllegalArgumentException("Birth date is mandatory");
        }
    }
}

