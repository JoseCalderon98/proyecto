package domain.services.loan;

import domain.models.loan.Loan;
import domain.repositories.loan.LoanRepository;

public class CreateLoan {
    private final LoanRepository repository;

    public CreateLoan(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan execute(Loan loan) {
        return repository.save(loan);
    }
}

