package application.config.security;

import application.adapters.persistence.sql.entities.UserEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaUserRepository;
import domain.models.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SpringDataJpaUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username)
                );

        if (userEntity.getUserStatus() != UserStatus.ACTIVE) {
            throw new UsernameNotFoundException("User is not active: " + username);
        }

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + userEntity.getSystemRole().name())
        );

        return new CustomUserDetails(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
