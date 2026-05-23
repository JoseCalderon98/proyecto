package domain.ports.transfer;

import domain.models.transfer.Transfer;
import java.util.Optional;
import java.util.List;

public interface TransferPort {
    Transfer save(Transfer transfer);
    Optional<Transfer> findById(int id);
    void delete(int id);
    List<Transfer> findAll();
    List<Transfer> findPendingTransfersOlderThan(java.time.LocalDateTime date);
}

