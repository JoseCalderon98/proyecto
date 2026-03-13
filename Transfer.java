import java.util.Date;

public class Transfer {
    private int transferId;
    private String originAccount;
    private String destinationAccount;
    private double amount;
    private Date creationDate;
    private Date approvalDate;
    private String transferStatus;
    private int creatorUserId;
    private int approverUserId;

    public Transfer(int transferId, String originAccount, String destinationAccount, double amount, Date creationDate, Date approvalDate, String transferStatus, int creatorUserId, int approverUserId) {
        this.transferId = transferId;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.creationDate = creationDate;
        this.approvalDate = approvalDate;
        this.transferStatus = transferStatus;
        this.creatorUserId = creatorUserId;
        this.approverUserId = approverUserId;
    }

    public int getTransferId() { return transferId; }
    public void setTransferId(int transferId) { this.transferId = transferId; }

    public String getOriginAccount() { return originAccount; }
    public void setOriginAccount(String originAccount) { this.originAccount = originAccount; }

    public String getDestinationAccount() { return destinationAccount; }
    public void setDestinationAccount(String destinationAccount) { this.destinationAccount = destinationAccount; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getApprovalDate() { return approvalDate; }
    public void setApprovalDate(Date approvalDate) { this.approvalDate = approvalDate; }

    public String getTransferStatus() { return transferStatus; }
    public void setTransferStatus(String transferStatus) { this.transferStatus = transferStatus; }

    public int getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(int creatorUserId) { this.creatorUserId = creatorUserId; }

    public int getApproverUserId() { return approverUserId; }
    public void setApproverUserId(int approverUserId) { this.approverUserId = approverUserId; }
}
