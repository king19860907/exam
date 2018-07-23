package com.majun.exam.base.util;

import com.majun.exam.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by majun on 2018/7/24.
 */
public class RequestUtils {

    public static final String SESSION_USER = "SESSION_USER";

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Session getSession(){
        return getSubject().getSession();
    }

    public static User getUser(){
        return (User) getSession().getAttribute(SESSION_USER);
    }

    public static void setUser(User user){
        getSession().setAttribute(SESSION_USER,user);
    }

}
