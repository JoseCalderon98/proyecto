package application.usecases;

import domain.events.LoanApprovedEvent;
import domain.models.loan.Loan;
import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;
import domain.ports.user.UserPort;
import java.util.Map;

public class ApproveLoanUseCase {

    private LoanPort loanPort;
    private EventPublisher eventPublisher;
    private UserPort userPort;

    public void setLoanPort(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setUserPort(UserPort userPort) {
        this.userPort = userPort;
    }

    public void execute(int loanId, int executorUserId, UserRole executorRole) {
        if (executorRole != UserRole.INTERNAL_ANALYST) {
            throw new SecurityException("Only an Internal Analyst can approve a loan");
        }

        // Validate that the executor user exists, is ACTIVE, and is actually an INTERNAL_ANALYST in the DB
        User executor = userPort.findById(executorUserId)
                .orElseThrow(() -> new SecurityException("Executor user not found in the system"));

        if (executor.getSystemRole() != UserRole.INTERNAL_ANALYST) {
            throw new SecurityException("The executor user's role in the database is not INTERNAL_ANALYST");
        }

        if (executor.getUserStatus() != UserStatus.ACTIVE) {
            throw new SecurityException("Executor user status must be ACTIVE to approve loans");
        }

        Loan loan = loanPort.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        loan.approve();
        
        loanPort.save(loan);

        LoanApprovedEvent event = new LoanApprovedEvent();
        event.setUserId(executorUserId);
        event.setUserRole(executorRole.name());
        event.setAffectedProductId(String.valueOf(loan.getLoanId()));
        event.setEventData(Map.of(
                "approvedAmount", loan.getApprovedAmount(),
                "interestRate", loan.getInterestRate(),
                "termMonths", loan.getTermMonths()
        ));

        eventPublisher.publish(event);
    }
}
