package application.adapters.api;

import application.adapters.api.requests.ApproveLoanRequest;
import application.adapters.api.requests.CreateLoanRequest;
import application.adapters.api.requests.DisburseLoanRequest;
import application.adapters.api.requests.RejectLoanRequest;
import application.usecases.ApproveLoanUseCase;
import application.usecases.CreateLoanUseCase;
import application.usecases.DisburseLoanUseCase;
import application.usecases.RejectLoanUseCase;
import application.usecases.FindLoanUseCase;
import domain.models.loan.Loan;
import domain.models.loan.LoanType;
import domain.models.user.UserRole;
import application.config.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private ApproveLoanUseCase approveLoanUseCase;

    @Autowired
    private DisburseLoanUseCase disburseLoanUseCase;

    @Autowired
    private RejectLoanUseCase rejectLoanUseCase;

    @Autowired
    private CreateLoanUseCase createLoanUseCase;

    @Autowired
    private FindLoanUseCase findLoanUseCase;

    @PostMapping("/{loanId}/approve")
    public ResponseEntity<String> approveLoan(
            @PathVariable("loanId") int loanId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        approveLoanUseCase.execute(loanId, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok("Loan approved successfully");
    }

    @PostMapping("/{loanId}/disburse")
    public ResponseEntity<String> disburseLoan(
            @PathVariable("loanId") int loanId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        disburseLoanUseCase.execute(loanId, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok("Loan disbursed successfully");
    }

    @PostMapping("/{loanId}/reject")
    public ResponseEntity<String> rejectLoan(
            @PathVariable("loanId") int loanId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        rejectLoanUseCase.execute(loanId, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok("Loan rejected successfully");
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@Valid @RequestBody CreateLoanRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Loan loan = new Loan();
        loan.setLoanType(LoanType.valueOf(request.getLoanType()));
        loan.setApplicantClientId(request.getApplicantClientId());
        loan.setRequestedAmount(request.getRequestedAmount());
        loan.setApprovedAmount(request.getRequestedAmount()); // Initially approved amount matches requested
        loan.setInterestRate(request.getInterestRate());
        loan.setTermMonths(request.getTermMonths());
        loan.setDestinationAccountForDisbursement(request.getDestinationAccountForDisbursement());

        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Loan createdLoan = createLoanUseCase.execute(loan, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok(createdLoan);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = findLoanUseCase.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<?> getLoanById(@PathVariable("loanId") int loanId) {
        return findLoanUseCase.findById(loanId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
