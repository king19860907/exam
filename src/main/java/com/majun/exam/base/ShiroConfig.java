package com.majun.exam.base;

import com.majun.exam.base.shiro.ExamAuthenticationFilter;
import com.majun.exam.base.shiro.UserRealm;
import com.majun.exam.dao.UserMapper;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by majun on 2018/7/23.
 */
@Configuration
//@ImportResource(locations={"classpath:context.xml"})
public class ShiroConfig {

    /**
     * 自定义shiro认证容器
     *
     * @return ShiroRealm
     */
    @Bean(name = "userRealm")
    public UserRealm getShiroRealm(@Qualifier("sessionManager") SessionManager sessionManager) {
        UserRealm realm = new UserRealm();
        CredentialsMatcher matcher = new AllowAllCredentialsMatcher();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * 配置自定义sessionID
     *
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        return sessionIdCookie;
    }

    /**
     * 会话session管理
     *
     * @return
     */
    @Bean(name = "sessionManager") //@Qualifier("sessionIdcookies")SimpleCookie cookie
    public SessionManager getSessionManager(@Qualifier("sessionIdCookie") SimpleCookie cookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //session失效时间
        sessionManager.setGlobalSessionTimeout(1440000);
        //自定义cookies 启用
        sessionManager.setSessionIdCookie(getSessionIdCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    /**
     * 安全管理器，权限管理，配置主要是Realm的管理认证
     *
     * @param realm
     * @param sessionManager
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm") UserRealm realm,
                                           @Qualifier("sessionManager") SessionManager sessionManager
    ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    /**
     * 过滤器，Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") SecurityManager securityManager,
            @Qualifier("examAuthenticationFilter") ExamAuthenticationFilter examAuthenticationFilter
            ) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc",examAuthenticationFilter);
        filter.setFilters(filterMap);

        Map<String, String> map = new LinkedHashMap<>();

        map.put("/**/*.css", "anon");
        map.put("/**/*.js", "anon");
        map.put("/**/*.map", "anon");
        map.put("/**/*.ico", "anon");
        map.put("/**/*.jpg", "anon");
        map.put("/**/*.png", "anon");
        map.put("/fonts/*", "anon");
        map.put("/font-awesome/**/*","anon");
        map.put("/api/init","anon");
        map.put("/logout","logout");
        map.put("/**", "authc");

        filter.setFilterChainDefinitionMap(map);
        filter.setLoginUrl("/");
        filter.setSuccessUrl("/list");
        return filter;
    }

    @Bean(name = "examAuthenticationFilter")
    public ExamAuthenticationFilter getExamAuthenticationFilter(){
        ExamAuthenticationFilter examAuthenticationFilter = new ExamAuthenticationFilter();
        examAuthenticationFilter.setUsernameParam("username");
        return examAuthenticationFilter;
    }


    @Bean
    public FilterRegistrationBean registration(ExamAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
