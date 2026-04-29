package application.adapters.api;

import application.usecases.ApproveLoanUseCase;
import application.usecases.DisburseLoanUseCase;
import domain.models.user.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final ApproveLoanUseCase approveLoanUseCase;
    private final DisburseLoanUseCase disburseLoanUseCase;

    public LoanController(ApproveLoanUseCase approveLoanUseCase, DisburseLoanUseCase disburseLoanUseCase) {
        this.approveLoanUseCase = approveLoanUseCase;
        this.disburseLoanUseCase = disburseLoanUseCase;
    }

    @PostMapping("/{loanId}/approve")
    public ResponseEntity<String> approveLoan(
            @PathVariable int loanId,
            @RequestHeader("X-User-Id") int userId,
            @RequestHeader("X-User-Role") String userRole) {
        
        try {
            approveLoanUseCase.execute(loanId, userId, UserRole.valueOf(userRole));
            return ResponseEntity.ok("Loan approved successfully");
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{loanId}/disburse")
    public ResponseEntity<String> disburseLoan(
            @PathVariable int loanId,
            @RequestHeader("X-User-Id") int userId,
            @RequestHeader("X-User-Role") String userRole) {
        
        try {
            disburseLoanUseCase.execute(loanId, userId, UserRole.valueOf(userRole));
            return ResponseEntity.ok("Loan disbursed successfully");
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
