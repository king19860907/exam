package com.majun.exam.dao;

import com.majun.exam.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by majun on 04/06/2018.
 */
@Mapper
@Repository
public interface UserDao {

    List<User> queryUsers();


}
