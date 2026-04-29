package domain.services;


import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;
import java.util.Optional;

public class FindLoan {
    private final LoanPort Port;

    public FindLoan(LoanPort Port) {
        this.Port = Port;
    }

    public Optional<Loan> byId(int id) {
        return Port.findById(id);
    }
}




