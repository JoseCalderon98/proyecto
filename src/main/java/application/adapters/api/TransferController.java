package application.adapters.api;

import application.adapters.api.requests.CreateTransferRequest;
import application.adapters.api.requests.ApproveTransferRequest;
import application.adapters.api.requests.RejectTransferRequest;
import application.usecases.ApproveTransferUseCase;
import application.usecases.CreateTransferUseCase;
import application.usecases.RejectTransferUseCase;
import domain.models.transfer.Transfer;
import domain.models.user.UserRole;
import application.config.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private CreateTransferUseCase createTransferUseCase;

    @Autowired
    private ApproveTransferUseCase approveTransferUseCase;

    @Autowired
    private RejectTransferUseCase rejectTransferUseCase;
    
    @PostMapping
    public ResponseEntity<Transfer> createTransfer(@Valid @RequestBody CreateTransferRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        
        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Transfer transfer = createTransferUseCase.execute(
                request.getOriginAccount(),
                request.getDestinationAccount(),
                request.getAmount(),
                userDetails.getUserId(),
                UserRole.valueOf(roleStr)
        );
        return ResponseEntity.ok(transfer);
    }

    @PostMapping("/{transferId}/approve")
    public ResponseEntity<String> approveTransfer(
            @PathVariable("transferId") int transferId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        
        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        approveTransferUseCase.execute(transferId, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok("Transfer approved successfully");
    }

    @PostMapping("/{transferId}/reject")
    public ResponseEntity<String> rejectTransfer(
            @PathVariable("transferId") int transferId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        
        String roleStr = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        rejectTransferUseCase.execute(transferId, userDetails.getUserId(), UserRole.valueOf(roleStr));
        return ResponseEntity.ok("Transfer rejected successfully");
    }
}
