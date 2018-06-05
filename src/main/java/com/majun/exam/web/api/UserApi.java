package com.majun.exam.web.api;

import com.majun.exam.pojo.User;
import com.majun.exam.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by majun on 04/06/2018.
 */
@RestController
@RequestMapping("/api")
public class UserApi {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> queryUsers(){
        return userService.selectAll();
    }

}
