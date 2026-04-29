package application.adapters.persistence.sql.entities;

import domain.models.transfer.TransferStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferId;

    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;

    @Enumerated(EnumType.STRING)
    private TransferStatus transferStatus;

    private int creatorUserId;
    private int approverUserId;

    // Default constructor for JPA
    public TransferEntity() {}

    // Getters and Setters
    public int getTransferId() { return transferId; }
    public void setTransferId(int transferId) { this.transferId = transferId; }

    public String getOriginAccount() { return originAccount; }
    public void setOriginAccount(String originAccount) { this.originAccount = originAccount; }

    public String getDestinationAccount() { return destinationAccount; }
    public void setDestinationAccount(String destinationAccount) { this.destinationAccount = destinationAccount; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public LocalDateTime getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDateTime approvalDate) { this.approvalDate = approvalDate; }

    public TransferStatus getTransferStatus() { return transferStatus; }
    public void setTransferStatus(TransferStatus transferStatus) { this.transferStatus = transferStatus; }

    public int getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(int creatorUserId) { this.creatorUserId = creatorUserId; }

    public int getApproverUserId() { return approverUserId; }
    public void setApproverUserId(int approverUserId) { this.approverUserId = approverUserId; }
}
