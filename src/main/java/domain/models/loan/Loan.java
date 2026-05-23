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

    public void approve() {
        if (this.loanStatus != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved");
        }
        this.loanStatus = LoanStatus.APPROVED;
        this.approvalDate = LocalDateTime.now();
    }

    public void reject() {
        if (this.loanStatus != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be rejected");
        }
        this.loanStatus = LoanStatus.REJECTED;
        this.approvalDate = LocalDateTime.now();
    }

    public void disburse() {
        if (this.loanStatus != LoanStatus.APPROVED) {
            throw new IllegalStateException("Only approved loans can be disbursed");
        }
        this.loanStatus = LoanStatus.DISBURSED;
        this.disbursementDate = LocalDateTime.now();
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

    public void validate() {
        if (requestedAmount == null || requestedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Requested amount must be greater than zero");
        }
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Interest rate must be greater than zero");
        }
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Term in months must be greater than zero");
        }
    }
}

