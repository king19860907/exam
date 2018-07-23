package com.majun.exam.dao.expand;

import com.majun.exam.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by majun on 2018/7/24.
 */
@Mapper
public interface UserExpandMapper{

    User getUserByName(@Param("name") String name);

}
