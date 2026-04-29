package application.usecases;

import domain.events.LoanDisbursedEvent;
import domain.models.account.BankAccount;
import domain.models.loan.Loan;
import domain.models.user.UserRole;
import domain.ports.account.BankAccountPort;
import domain.ports.events.EventPublisher;
import domain.ports.loan.LoanPort;

public class DisburseLoanUseCase {

    private final LoanPort loanPort;
    private final BankAccountPort bankAccountPort;
    private final EventPublisher eventPublisher;

    public DisburseLoanUseCase(LoanPort loanPort, BankAccountPort bankAccountPort, EventPublisher eventPublisher) {
        this.loanPort = loanPort;
        this.bankAccountPort = bankAccountPort;
        this.eventPublisher = eventPublisher;
    }

    public void execute(int loanId, int executorUserId, UserRole executorRole) {
        if (executorRole != UserRole.INTERNAL_ANALYST) {
            throw new SecurityException("Only an Internal Analyst can disburse a loan");
        }

        Loan loan = loanPort.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        BankAccount destinationAccount = bankAccountPort.findById(loan.getDestinationAccountForDisbursement())
                .orElseThrow(() -> new IllegalArgumentException("Destination bank account not found"));

        // Disburse loan
        loan.disburse();
        
        // Deposit into account
        destinationAccount.deposit(loan.getApprovedAmount());

        // Save changes
        bankAccountPort.save(destinationAccount);
        loanPort.save(loan);

        // Publish event
        eventPublisher.publish(new LoanDisbursedEvent(loan, executorUserId, executorRole.name()));
    }
}
