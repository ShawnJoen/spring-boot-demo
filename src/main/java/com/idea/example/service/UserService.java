package com.idea.example.service;

import com.idea.example.domain.dto.User;
import com.idea.example.enums.CodeEnum;

public interface UserService {
    User findUserByEmail(String email);
    CodeEnum saveUser(String email, String pw, String fingerprint);
}
