package domain.models.loan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan {
    private int loanId;
    private LoanType loanType;
    private String applicantClientId;
    private BigDecimal requestedAmount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private int termMonths;
    private LoanStatus loanStatus;
    private LocalDateTime approvalDate;
    private LocalDateTime disbursementDate;
    private String destinationAccountForDisbursement;
    private int creatorUserId;

    public Loan(int loanId, LoanType loanType, String applicantClientId, BigDecimal requestedAmount, BigDecimal approvedAmount, BigDecimal interestRate, int termMonths, LoanStatus loanStatus, LocalDateTime approvalDate, LocalDateTime disbursementDate, String destinationAccountForDisbursement, int creatorUserId) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.applicantClientId = applicantClientId;
        this.requestedAmount = requestedAmount;
        this.approvedAmount = approvedAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.loanStatus = loanStatus;
        this.approvalDate = approvalDate;
        this.disbursementDate = disbursementDate;
        this.destinationAccountForDisbursement = destinationAccountForDisbursement;
        this.creatorUserId = creatorUserId;
    }
    
    public Loan() {
    }

    public void approve() {
        if (this.loanStatus != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved");
        }
        this.loanStatus = LoanStatus.APPROVED;
        this.approvalDate = LocalDateTime.now();
    }

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public LoanType getLoanType() { return loanType; }
    public void setLoanType(LoanType loanType) { this.loanType = loanType; }

    public String getApplicantClientId() { return applicantClientId; }
    public void setApplicantClientId(String applicantClientId) { this.applicantClientId = applicantClientId; }

    public BigDecimal getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(BigDecimal requestedAmount) { this.requestedAmount = requestedAmount; }

    public BigDecimal getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(BigDecimal approvedAmount) { this.approvedAmount = approvedAmount; }

    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public int getTermMonths() { return termMonths; }
    public void setTermMonths(int termMonths) { this.termMonths = termMonths; }

    public LoanStatus getLoanStatus() { return loanStatus; }
    public void setLoanStatus(LoanStatus loanStatus) { this.loanStatus = loanStatus; }

    public LocalDateTime getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDateTime approvalDate) { this.approvalDate = approvalDate; }

    public LocalDateTime getDisbursementDate() { return disbursementDate; }
    public void setDisbursementDate(LocalDateTime disbursementDate) { this.disbursementDate = disbursementDate; }

    public String getDestinationAccountForDisbursement() { return destinationAccountForDisbursement; }
    public void setDestinationAccountForDisbursement(String destinationAccountForDisbursement) { this.destinationAccountForDisbursement = destinationAccountForDisbursement; }

    public int getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(int creatorUserId) { this.creatorUserId = creatorUserId; }
}

