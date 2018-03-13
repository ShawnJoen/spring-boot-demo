package com.idea.example.spring_security;

import com.idea.example.domain.dto.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPw(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {return user.getId();}

    @Override
    public String toString() {
        return "CurrentUser{user " + user + "} " + super.toString();
    }

}

