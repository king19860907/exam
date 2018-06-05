package com.majun.exam.web.api;

import com.majun.exam.pojo.User;
import com.majun.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by majun on 04/06/2018.
 */
@RestController
@RequestMapping("/api")
public class UserApi {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> queryUsers(){
        return userService.queryUsers();
    }

}
