package application.adapters.persistence.sql.entities;

import domain.models.loan.LoanStatus;
import domain.models.loan.LoanType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    private String applicantClientId;
    private BigDecimal requestedAmount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private int termMonths;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private LocalDateTime approvalDate;
    private LocalDateTime disbursementDate;
    private String destinationAccountForDisbursement;
    private int creatorUserId;

    // Default constructor for JPA
    public LoanEntity() {}

    // Getters and Setters
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
