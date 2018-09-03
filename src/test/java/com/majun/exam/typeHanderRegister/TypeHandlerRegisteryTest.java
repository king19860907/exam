package com.majun.exam.typeHanderRegister;

import com.majun.exam.dao.UserMapper;
import com.majun.exam.pojo.User;
import com.majun.metaClass.Order;
import org.apache.ibatis.type.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by majun on 2018/8/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeHandlerRegisteryTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){

        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }

    @Test
    public void getInstence(){
        TypeHandlerRegistry registery = new TypeHandlerRegistry();
        TypeHandler  typeHandler= registery.getInstance(null, BooleanTypeHandler.class);
        System.out.println(typeHandler);
    }

    @Test
    public void getTypeHandler(){
        TypeHandlerRegistry registery = new TypeHandlerRegistry();
        TypeHandler typeHandler = registery.getTypeHandler(String.class);
        System.out.println(typeHandler);

        //第一次执行该方法时会将Order.class 加入到TYPE_HANDLER_MAP中
        typeHandler = registery.getTypeHandler(Order.class);
        System.out.println(typeHandler);

        //第二次执行该方法时,直接从TYPE_HANDLER_MAP中拿出对应的TypeHandler,为null
        typeHandler = registery.getTypeHandler(Order.class);
        System.out.println(typeHandler);

        typeHandler = registery.getTypeHandler(String.class, JdbcType.CLOB);
        System.out.println(typeHandler);

    }

    @Test
    public void registerAliases(){
        TypeAliasRegistry registry = new TypeAliasRegistry();
        registry.registerAliases("com.majun.exam.pojo");
    }

}
