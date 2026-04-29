package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaLoanRepository extends JpaRepository<LoanEntity, Integer> {
}
