package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.TransferEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaTransferRepository;
import domain.models.transfer.Transfer;
import domain.models.transfer.TransferStatus;
import domain.ports.transfer.TransferPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransferPersistenceAdapter implements TransferPort {

    private final SpringDataJpaTransferRepository transferRepository;

    public TransferPersistenceAdapter(SpringDataJpaTransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        TransferEntity entity = toEntity(transfer);
        TransferEntity savedEntity = transferRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Transfer> findById(int id) {
        return transferRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(int id) {
        transferRepository.deleteById(id);
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transfer> findPendingTransfersOlderThan(LocalDateTime date) {
        return transferRepository.findByTransferStatusAndCreationDateBefore(TransferStatus.PENDING, date)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private TransferEntity toEntity(Transfer transfer) {
        TransferEntity entity = new TransferEntity();
        entity.setTransferId(transfer.getTransferId());
        entity.setOriginAccount(transfer.getOriginAccount());
        entity.setDestinationAccount(transfer.getDestinationAccount());
        entity.setAmount(transfer.getAmount());
        entity.setCreationDate(transfer.getCreationDate());
        entity.setApprovalDate(transfer.getApprovalDate());
        entity.setTransferStatus(transfer.getTransferStatus());
        entity.setCreatorUserId(transfer.getCreatorUserId());
        entity.setApproverUserId(transfer.getApproverUserId());
        return entity;
    }

    private Transfer toDomain(TransferEntity entity) {
        return new Transfer(
                entity.getTransferId(),
                entity.getOriginAccount(),
                entity.getDestinationAccount(),
                entity.getAmount(),
                entity.getCreationDate(),
                entity.getApprovalDate(),
                entity.getTransferStatus(),
                entity.getCreatorUserId(),
                entity.getApproverUserId()
        );
    }
}
