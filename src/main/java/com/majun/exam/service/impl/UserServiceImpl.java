package com.majun.exam.service.impl;

import com.majun.exam.dao.UserDao;
import com.majun.exam.pojo.User;
import com.majun.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by majun on 04/06/2018.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUsers() {
        return userDao.queryUsers();
    }

}
