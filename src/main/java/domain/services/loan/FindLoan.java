package domain.services.loan;

import domain.models.loan.Loan;
import domain.repositories.loan.LoanRepository;
import java.util.Optional;

public class FindLoan {
    private final LoanRepository repository;

    public FindLoan(LoanRepository repository) {
        this.repository = repository;
    }

    public Optional<Loan> byId(int id) {
        return repository.findById(id);
    }
}

