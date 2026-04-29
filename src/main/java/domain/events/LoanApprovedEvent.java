package domain.events;

import domain.models.loan.Loan;
import java.util.Map;

public class LoanApprovedEvent extends BaseDomainEvent {
    public LoanApprovedEvent(Loan loan, int approverUserId, String approverRole) {
        super(
            "LOAN_APPROVED",
            approverUserId,
            approverRole,
            String.valueOf(loan.getLoanId()),
            Map.of(
                "approvedAmount", loan.getApprovedAmount(),
                "interestRate", loan.getInterestRate(),
                "termMonths", loan.getTermMonths()
            )
        );
    }
}
