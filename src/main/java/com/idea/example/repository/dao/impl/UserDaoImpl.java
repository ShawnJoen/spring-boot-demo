package com.idea.example.repository.dao.impl;

import com.idea.example.domain.dto.FingerPrint;
import com.idea.example.domain.dto.User;
import com.idea.example.repository.dao.BaseDaoImpl;
import com.idea.example.repository.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public User findUserByEmail(String email) {
        return getSqlSession().selectOne(getStatement("findUserByEmail"), email);
    }

    @Override
    public int saveUser(User user) {
        return getSqlSession().insert(getStatement("saveUser"), user);
    }

    @Override
    public int saveFingerPrint(FingerPrint fingerPrint) {
        return getSqlSession().insert(getStatement("saveFingerPrint"), fingerPrint);
    }
}
