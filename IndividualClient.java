import java.util.Date;

public class IndividualClient extends Client {
    private Date birthDate;

    public IndividualClient(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
