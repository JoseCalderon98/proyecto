package domain.services.loan;

import domain.repositories.loan.LoanRepository;

public class DeleteLoan {
    private final LoanRepository repository;

    public DeleteLoan(LoanRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        repository.delete(id);
    }
}

