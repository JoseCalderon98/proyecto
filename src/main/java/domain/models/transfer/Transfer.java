package domain.models.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer {
    private int transferId;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;
    private TransferStatus transferStatus;
    private int creatorUserId;
    private int approverUserId;


    public void approve(int approverId) {
        if (this.transferStatus != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only pending approval transfers can be approved");
        }
        this.transferStatus = TransferStatus.EXECUTED;
        this.approvalDate = LocalDateTime.now();
        this.approverUserId = approverId;
    }

    public void reject(int approverId) {
        if (this.transferStatus != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only pending approval transfers can be rejected");
        }
        this.transferStatus = TransferStatus.REJECTED;
        this.approvalDate = LocalDateTime.now();
        this.approverUserId = approverId;
    }

    public void expire() {
        if (this.transferStatus != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only pending approval transfers can be expired");
        }
        this.transferStatus = TransferStatus.EXPIRED;
    }

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
