package domain.services;


import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;

public class CreateLoan {
    private final LoanPort Port;

    public CreateLoan(LoanPort Port) {
        this.Port = Port;
    }

    public Loan execute(Loan loan) {
        return Port.save(loan);
    }
}




