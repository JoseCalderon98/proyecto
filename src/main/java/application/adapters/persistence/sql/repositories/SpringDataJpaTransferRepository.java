package application.adapters.persistence.sql.repositories;

import application.adapters.persistence.sql.entities.TransferEntity;
import domain.models.transfer.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataJpaTransferRepository extends JpaRepository<TransferEntity, Integer> {
    List<TransferEntity> findByTransferStatusAndCreationDateBefore(TransferStatus status, LocalDateTime date);
}
