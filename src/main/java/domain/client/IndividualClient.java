package domain.client;

import java.time.LocalDate;

public class IndividualClient extends Client {
    private LocalDate birthDate;

    public IndividualClient(String fullName, String identification, ClientStatus clientStatus, LocalDate birthDate) {
        super(fullName, identification, clientStatus);
        this.birthDate = birthDate;
    }

    // Constructor que usa name y identificationNumber por retrocompatibilidad/requisitos
    public IndividualClient(String name, String identificationNumber, LocalDate birthDate) {
        super(name, identificationNumber, ClientStatus.ACTIVE); // Por defecto
        this.birthDate = birthDate;
    }

    public IndividualClient() {
        super();
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
}
