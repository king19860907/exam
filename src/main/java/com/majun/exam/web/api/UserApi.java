package com.majun.exam.web.api;

import com.github.pagehelper.PageInfo;
import com.majun.exam.pojo.User;
import com.majun.exam.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by majun on 04/06/2018.
 */
@RestController
@RequestMapping("/api")
public class UserApi {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public PageInfo<User> queryUsers(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return userService.selectPage(pageNum,pageSize);
    }

}
