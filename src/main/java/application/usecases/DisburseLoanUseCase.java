package application.usecases;

import domain.events.LoanDisbursedEvent;
import domain.models.account.BankAccount;
import domain.models.loan.Loan;
import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;
import domain.ports.user.UserPort;
import application.adapters.api.requests.BankAccountNotFoundException;
import application.adapters.api.requests.InactiveBankAccountException;
import application.adapters.api.requests.UnauthorizedOwnerException;
import application.adapters.api.requests.UnauthorizedRoleException;

public class DisburseLoanUseCase {

    private LoanPort loanPort;
    private BankAccountPort bankAccountPort;
    private EventPublisher eventPublisher;
    private UserPort userPort;

    public void setLoanPort(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public void setBankAccountPort(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public void execute(int loanId, int executorUserId, UserRole executorRole) {
        if (executorRole != UserRole.INTERNAL_ANALYST) {
            throw new UnauthorizedRoleException("Only an Internal Analyst can disburse a loan");
        }

        // Validate that the executor user exists, is ACTIVE, and is actually an INTERNAL_ANALYST in the DB
        User executor = userPort.findById(executorUserId)
                .orElseThrow(() -> new SecurityException("Executor user not found in the system"));

        if (executor.getSystemRole() != UserRole.INTERNAL_ANALYST) {
            throw new SecurityException("The executor user's role in the database is not INTERNAL_ANALYST");
        }

        if (executor.getUserStatus() != UserStatus.ACTIVE) {
            throw new SecurityException("Executor user status must be ACTIVE to disburse loans");
        }

        Loan loan = loanPort.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        BankAccount destinationAccount = bankAccountPort.findById(loan.getDestinationAccountForDisbursement())
                .orElseThrow(() -> new BankAccountNotFoundException("Destination bank account not found: " + loan.getDestinationAccountForDisbursement()));

        if (destinationAccount.getAccountStatus() != domain.models.account.AccountStatus.ACTIVE) {
            throw new InactiveBankAccountException("Destination account is not active");
        }
        
        if (destinationAccount.getHolderId() == null || !destinationAccount.getHolderId().equals(loan.getApplicantClientId())) {
            throw new UnauthorizedOwnerException("Destination account does not belong to the loan applicant");
        }

        // Disburse loan
        loan.disburse();
        
        // Deposit into account
        destinationAccount.deposit(loan.getApprovedAmount());

        // Save changes
        bankAccountPort.save(destinationAccount);
        loanPort.save(loan);

        // Publish event
        LoanDisbursedEvent event = new LoanDisbursedEvent();
        event.setUserId(executorUserId);
        event.setUserRole(executorRole.name());
        event.setAffectedProductId(String.valueOf(loan.getLoanId()));
        event.setEventData(java.util.Map.of("disbursedAmount", loan.getApprovedAmount(), "destinationAccount", loan.getDestinationAccountForDisbursement()));
        eventPublisher.publish(event);
    }
}
