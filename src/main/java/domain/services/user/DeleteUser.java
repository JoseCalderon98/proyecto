package domain.services.user;

import domain.repositories.user.UserRepository;

public class DeleteUser {
    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(int id) {
        userRepository.delete(id);
    }
}

