package com.idea.example.spring_security;

import com.idea.example.domain.dto.User;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Authenticating user with loginId {}", email);
        User user = Optional.ofNullable(
                    userService.findUserByEmail(email)
                ).orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User with email=%s was not found", email))
                );

        return new CurrentUser(user);
    }

}

