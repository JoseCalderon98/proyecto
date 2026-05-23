package application.adapters.api;

import application.adapters.api.requests.AuthRequest;
import application.adapters.api.response.AuthResponse;
import application.config.security.CustomUserDetails;
import application.config.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        // Extract the role. Assuming there's only one role, and it's prefixed with "ROLE_"
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        String jwt = tokenProvider.generateToken(authentication, userDetails.getUserId(), role);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
