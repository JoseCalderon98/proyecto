package application.adapters.api;

import application.usecases.ApproveTransferUseCase;
import application.usecases.CreateTransferUseCase;
import domain.models.user.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final CreateTransferUseCase createTransferUseCase;
    private final ApproveTransferUseCase approveTransferUseCase;

    public TransferController(CreateTransferUseCase createTransferUseCase, ApproveTransferUseCase approveTransferUseCase) {
        this.createTransferUseCase = createTransferUseCase;
        this.approveTransferUseCase = approveTransferUseCase;
    }

    @PostMapping
    public ResponseEntity<String> createTransfer(
            @RequestBody CreateTransferRequest request,
            @RequestHeader("X-User-Id") int userId,
            @RequestHeader("X-User-Role") String userRole) {
        
        try {
            createTransferUseCase.execute(
                    request.originAccount(),
                    request.destinationAccount(),
                    request.amount(),
                    userId,
                    UserRole.valueOf(userRole)
            );
            return ResponseEntity.ok("Transfer creation processed");
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{transferId}/approve")
    public ResponseEntity<String> approveTransfer(
            @PathVariable int transferId,
            @RequestHeader("X-User-Id") int userId,
            @RequestHeader("X-User-Role") String userRole) {
        
        try {
            approveTransferUseCase.execute(transferId, userId, UserRole.valueOf(userRole));
            return ResponseEntity.ok("Transfer approved successfully");
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

record CreateTransferRequest(String originAccount, String destinationAccount, BigDecimal amount) {}
