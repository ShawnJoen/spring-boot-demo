package com.idea.example.spring_security;

import com.idea.example.domain.dto.User;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException{
        log.info("Authenticating user with loginId {}", email);
        User user = Optional.ofNullable(
                    userService.findUserByEmail(email)
                ).orElseThrow(() ->
                        new BadCredentialsException(String.format("User with email=%s was not found", email))
                );

        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final String securityCode = attr.getRequest().getParameter("securityCode");
        if (!"AAAA".equals(securityCode)) {
            throw new BadCredentialsException("登入秘钥错误。");
        }

        return new CurrentUser(user);
    }

}

