package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.IndividualClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaIndividualClientRepository extends JpaRepository<IndividualClientEntity, Integer> {
}
