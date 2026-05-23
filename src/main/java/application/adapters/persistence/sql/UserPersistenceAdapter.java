package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.UserEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaUserRepository;
import domain.models.user.User;
import domain.ports.user.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter implements UserPort {

    @Autowired
    private SpringDataJpaUserRepository repository;

    private User toDomain(UserEntity entity) {
        if (entity == null)
            return null;
        User user = new User();
        user.setUserId(entity.getUserId());
        user.setRelatedId(entity.getRelatedId());
        user.setFullName(entity.getFullName());
        user.setIdentification(entity.getIdentification());
        user.setEmail(entity.getEmail());
        user.setPhone(entity.getPhone());
        user.setAddress(entity.getAddress());
        user.setSystemRole(entity.getSystemRole());
        user.setUserStatus(entity.getUserStatus());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        return user;
    }

    private UserEntity toEntity(User user) {
        if (user == null)
            return null;
        UserEntity entity = new UserEntity();
        if (user.getUserId() > 0) {
            entity.setUserId(user.getUserId());
        }
        entity.setRelatedId(user.getRelatedId());
        entity.setFullName(user.getFullName());
        entity.setIdentification(user.getIdentification());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        entity.setAddress(user.getAddress());
        entity.setSystemRole(user.getSystemRole());
        entity.setUserStatus(user.getUserStatus());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        return entity;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = repository.save(entity);
        user.setUserId(savedEntity.getUserId());
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }
}
