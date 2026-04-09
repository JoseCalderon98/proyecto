package domain.services.user;

import domain.models.user.User;
import domain.repositories.user.UserRepository;
import java.util.Optional;

public class FindUser {
    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> byId(int id) {
        return userRepository.findById(id);
    }
}

