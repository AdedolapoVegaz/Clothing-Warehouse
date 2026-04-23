package ca.humberPolytechnic.Ade.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.humberPolytechnic.Ade.model.AppUser;
import ca.humberPolytechnic.Ade.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String cleanRole = appUser.getRole().replace("ROLE_", "");

        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(cleanRole)
                .build();
    }
}