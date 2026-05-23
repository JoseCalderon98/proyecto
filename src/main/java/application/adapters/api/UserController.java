package application.adapters.api;

import domain.models.user.User;
import domain.models.user.UserRole;
import domain.models.user.UserStatus;
import application.adapters.api.requests.CreateUserRequest;
import application.usecases.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = new User();
        user.setRelatedId(request.getRelatedId());
        user.setFullName(request.getFullName());
        user.setIdentification(request.getIdentification());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setSystemRole(UserRole.valueOf(request.getSystemRole()));
        user.setUserStatus(UserStatus.valueOf(request.getUserStatus()));
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User createdUser = createUserUseCase.execute(user);
        return ResponseEntity.ok(createdUser);
    }
}
