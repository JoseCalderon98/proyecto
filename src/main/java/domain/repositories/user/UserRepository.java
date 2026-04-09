package domain.repositories.user;

import domain.models.user.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(int id);
    void delete(int id);
    List<User> findAll();
}
