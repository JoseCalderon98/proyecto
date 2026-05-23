package application.usecases;

import domain.models.loan.Loan;
import domain.ports.loan.LoanPort;
import java.util.List;
import java.util.Optional;

public class FindLoanUseCase {

    private LoanPort loanPort;

    public void setLoanPort(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public Optional<Loan> findById(int loanId) {
        return loanPort.findById(loanId);
    }

    public List<Loan> findAll() {
        return loanPort.findAll();
    }
}
