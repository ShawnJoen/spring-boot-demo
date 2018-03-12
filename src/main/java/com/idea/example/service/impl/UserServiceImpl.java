package com.idea.example.service.impl;

import com.idea.example.domain.dto.FingerPrint;
import com.idea.example.domain.dto.User;
import com.idea.example.enums.ActiveEnum;
import com.idea.example.enums.CodeEnum;
import com.idea.example.repository.dao.UserDao;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public CodeEnum saveUser(String email, String pw, String fingerprint) {

        User userExist = this.findUserByEmail(email);
        if (userExist != null) {
            return CodeEnum.FAIL;
        }

        User user = new User();
        user.setEmail(email);
        user.setPw(pw);
        user.setOtpHash("");
        user.setActive(ActiveEnum.Y);
        user.setRegDate(LocalDateTime.now());
        userDao.saveUser(user);

        FingerPrint fingerPrint = new FingerPrint();
        fingerPrint.setUserId(user.getId());
        fingerPrint.setHashKey("");
        userDao.saveFingerPrint(fingerPrint);

        return CodeEnum.SUCCESS;
    }
}
