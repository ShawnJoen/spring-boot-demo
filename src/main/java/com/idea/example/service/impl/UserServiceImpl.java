package com.idea.example.service.impl;

import com.idea.example.annotation.SoftTransational;
import com.idea.example.domain.dto.FingerPrint;
import com.idea.example.domain.dto.User;
import com.idea.example.enums.ActiveEnum;
import com.idea.example.enums.CodeEnum;
import com.idea.example.repository.dao.UserDao;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;//Spring Security需配置Security Config

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @SoftTransational
    @Override
    public String saveUser(String email, String pw, String fingerprint) {

        User userExist = this.findUserByEmail(email);
        if (userExist != null) {
            return "redirect:/user/register?msg=already";
            /*
            * TODO return JsonData
            * */
            //return CodeEnum.FAIL;
        }

        User user = new User();
        user.setEmail(email);
        user.setPw(bCryptPasswordEncoder.encode(pw));
        user.setActive(ActiveEnum.Y);
        user.setRegDate(LocalDateTime.now());
        userDao.saveUser(user);

        FingerPrint fingerPrint = new FingerPrint();
        fingerPrint.setUserId(user.getId());
        fingerPrint.setHashKey(bCryptPasswordEncoder.encode(fingerprint));
        userDao.saveFingerPrint(fingerPrint);

        return "redirect:/user/register?msg=succeed";
        /*
        * TODO return JsonData
        * */
        //return CodeEnum.SUCCESS;
    }
}
