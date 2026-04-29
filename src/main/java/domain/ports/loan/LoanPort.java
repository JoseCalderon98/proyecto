package domain.ports.loan;

import domain.models.loan.Loan;
import java.util.Optional;
import java.util.List;

public interface LoanPort {
    Loan save(Loan loan);
    Optional<Loan> findById(int id);
    void delete(int id);
    List<Loan> findAll();
}

