package com.idea.example.repository.dao;

import com.idea.example.domain.dto.FingerPrint;
import com.idea.example.domain.dto.User;

public interface UserDao {
    User findUserByEmail(String email);
    int saveUser(User user);
    int saveFingerPrint(FingerPrint fingerPrint);
}
