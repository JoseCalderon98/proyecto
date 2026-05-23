package domain.services;


import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;
import java.util.Optional;

public class FindLoan {
    private LoanPort Port;
    public void setPort(LoanPort Port) {
        this.Port = Port;
    }

    public Optional<Loan> byId(int id) {
        return Port.findById(id);
    }
}





