package domain.events;

import domain.models.loan.Loan;
import java.util.Map;

public class LoanDisbursedEvent extends BaseDomainEvent {
    public LoanDisbursedEvent(Loan loan, int disburserUserId, String disburserRole) {
        super(
            "LOAN_DISBURSED",
            disburserUserId,
            disburserRole,
            String.valueOf(loan.getLoanId()),
            Map.of(
                "disbursedAmount", loan.getApprovedAmount(),
                "destinationAccount", loan.getDestinationAccountForDisbursement()
            )
        );
    }
}
