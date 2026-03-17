package domain.loan;

import java.util.Date;

public class Loan {
    private int loanId;
    private String loanType;
    private String applicantClientId;
    private double requestedAmount;
    private double approvedAmount;
    private double interestRate;
    private int termMonths;
    private String loanStatus;
    private Date approvalDate;
    private Date disbursementDate;
    private String destinationAccountForDisbursement;
    private int creatorUserId;

    public Loan(int loanId, String loanType, String applicantClientId, double requestedAmount, double approvedAmount, double interestRate, int termMonths, String loanStatus, Date approvalDate, Date disbursementDate, String destinationAccountForDisbursement, int creatorUserId) {
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

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public String getApplicantClientId() { return applicantClientId; }
    public void setApplicantClientId(String applicantClientId) { this.applicantClientId = applicantClientId; }

    public double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(double requestedAmount) { this.requestedAmount = requestedAmount; }

    public double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(double approvedAmount) { this.approvedAmount = approvedAmount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public int getTermMonths() { return termMonths; }
    public void setTermMonths(int termMonths) { this.termMonths = termMonths; }

    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }

    public Date getApprovalDate() { return approvalDate; }
    public void setApprovalDate(Date approvalDate) { this.approvalDate = approvalDate; }

    public Date getDisbursementDate() { return disbursementDate; }
    public void setDisbursementDate(Date disbursementDate) { this.disbursementDate = disbursementDate; }

    public String getDestinationAccountForDisbursement() { return destinationAccountForDisbursement; }
    public void setDestinationAccountForDisbursement(String destinationAccountForDisbursement) { this.destinationAccountForDisbursement = destinationAccountForDisbursement; }

    public int getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(int creatorUserId) { this.creatorUserId = creatorUserId; }
}
