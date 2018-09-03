package com.majun.exam.service.impl;

import com.majun.exam.dao.UserMapper;
import com.majun.exam.dao.expand.UserExpandMapper;
import com.majun.exam.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * Created by majun on 2018/8/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryUserServiceTest {


    @Autowired
    private UserExpandMapper userExpandMapper;

    @Test
    public void test(){

        User user = userExpandMapper.getUserByName("aaa");
        System.out.println(user);


    }

}
