package domain.repositories.transfer;

import domain.models.transfer.Transfer;
import java.util.Optional;
import java.util.List;

public interface TransferRepository {
    Transfer save(Transfer transfer);
    Optional<Transfer> findById(int id);
    void delete(int id);
    List<Transfer> findAll();
}
