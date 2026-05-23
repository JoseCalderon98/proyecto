package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByIdentification(String identification);
    Optional<UserEntity> findByUsername(String username);
}
