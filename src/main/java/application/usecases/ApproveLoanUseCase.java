package application.usecases;

import domain.events.LoanApprovedEvent;
import domain.models.loan.Loan;
import domain.models.user.UserRole;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;

public class ApproveLoanUseCase {

    private final LoanPort loanPort;
    private final EventPublisher eventPublisher;

    public ApproveLoanUseCase(LoanPort loanPort, EventPublisher eventPublisher) {
        this.loanPort = loanPort;
        this.eventPublisher = eventPublisher;
    }

    public void execute(int loanId, int executorUserId, UserRole executorRole) {
        if (executorRole != UserRole.INTERNAL_ANALYST) {
            throw new SecurityException("Only an Internal Analyst can approve a loan");
        }

        Loan loan = loanPort.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        loan.approve();
        
        loanPort.save(loan);

        eventPublisher.publish(new LoanApprovedEvent(loan, executorUserId, executorRole.name()));
    }
}
