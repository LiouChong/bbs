package com.bysj.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 刘冲
 * @Description:
 * @Date: 2019/3/3 12:48
 */
@Configuration
public class ShiroConfiguration {


    /**
     * 将自己验证方式加入容器
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置自定义拦截器,跳转到成功页面
        Map map = new LinkedHashMap();
        map.put("authc", new MyFormAuthticationFilter());
        shiroFilterFactoryBean.setFilters(map);

        //拦截器
        Map<String, String> filterChainDefinetionMap = new LinkedHashMap<>();



        // 前端采用thyleaf，因此不能直接使用static，必须准确到每个静态目录。
        filterChainDefinetionMap.put("/css/**", "anon");
        filterChainDefinetionMap.put("/fonts/**", "anon");
        filterChainDefinetionMap.put("/articles-list/**", "anon");
        filterChainDefinetionMap.put("/assets/**", "anon");
        filterChainDefinetionMap.put("/images/**", "anon");
        filterChainDefinetionMap.put("/js/**", "anon");
        filterChainDefinetionMap.put("/user/**", "anon");

        // 具体controller的链接
        // 首页跳转允许
        filterChainDefinetionMap.put("/post/query/recommended", "anon");
        filterChainDefinetionMap.put("/user/login", "anon");
        // 简单搜索功能允许
//        filterChainDefinetionMap.put("/post/query/simple", "anon");


        // 退出登录页面
        filterChainDefinetionMap.put("/logout", "logout");


        shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功后跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/post/query/recommended");

        filterChainDefinetionMap.put("/**", "authc");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinetionMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 对密码加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }


}