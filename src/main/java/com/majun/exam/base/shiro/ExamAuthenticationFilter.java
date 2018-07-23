package com.majun.exam.base.shiro;

import com.majun.exam.base.util.RequestUtils;
import com.majun.exam.dao.UserMapper;
import com.majun.exam.dao.expand.UserExpandMapper;
import com.majun.exam.pojo.User;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;

/**
 * Created by majun on 2018/7/24.
 */
public class ExamAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private UserExpandMapper userExpandMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String name = token.getPrincipal().toString();
        User user = userExpandMapper.getUserByName(name);
        if(user == null){
            user = new User();
            user.setUserName(name);
            user.setCreateTime(new Date());
            userMapper.insert(user);
        }
        RequestUtils.setUser(user);
        return super.onLoginSuccess(token, subject, request, response);
    }

}
