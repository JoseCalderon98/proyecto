package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.CorporateClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCorporateClientRepository extends JpaRepository<CorporateClientEntity, Integer> {
}
