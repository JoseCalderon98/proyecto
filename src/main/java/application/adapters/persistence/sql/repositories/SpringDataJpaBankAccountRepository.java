package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaBankAccountRepository extends JpaRepository<BankAccountEntity, String> {
}
