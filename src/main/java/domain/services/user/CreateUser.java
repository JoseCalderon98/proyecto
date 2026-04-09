package domain.services.user;

import domain.models.user.User;
import domain.repositories.user.UserRepository;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        // Here you would add business rules, validation, etc.
        return userRepository.save(user);
    }
}

