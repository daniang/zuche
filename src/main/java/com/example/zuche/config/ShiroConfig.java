package com.example.zuche.config;

import com.example.zuche.properties.FebsProperties;
import com.example.zuche.properties.ShiroProperties;
import com.example.zuche.realm.CustomRealm;
import com.example.zuche.realm.ShiroRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.stream.Collectors;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2021/12/27 14:39
 */
//暂时 解除shiro配置
@Configuration
public class ShiroConfig {

//    //将自己的验证方式加入容器
//    @Autowired
//    private CustomRealm customRealm;

    // 将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }


    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();

        return shiroRealm;

    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;

    }


    /**
     * 配置session的会话时间
     *
     * @return
     */
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();

        defaultWebSessionManager.setGlobalSessionTimeout(1000 * 60);//会话过期时间
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);

        return defaultWebSessionManager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setSessionManager(getDefaultWebSessionManager());

//        securityManager.setRealm(shiroRealm());
        securityManager.setRealm(myShiroRealm());

        return securityManager;
    }

    //Filter 工厂，设置对应的过滤条件和跳转条件
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean( SecurityManager securityManager,FebsProperties febsProperties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        ShiroProperties shiro = febsProperties.getShiro();
//
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        // 登录的 url
//        shiroFilterFactoryBean.setLoginUrl(shiro.getLoginUrl());
//        // 登录成功后跳转的 url
//        shiroFilterFactoryBean.setSuccessUrl(shiro.getSuccessUrl());
//        // 未授权 url
//        shiroFilterFactoryBean.setUnauthorizedUrl(shiro.getUnauthorizedUrl());
//        // 设置免认证 url
//        LinkedHashMap<String, String> filterChainDefinitionMap;
//        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(shiro.getAnonUrl(), ",");
//        filterChainDefinitionMap = Arrays.stream(anonUrls).collect(Collectors.toMap(url -> url, url -> "anon", (a, b) -> b, LinkedHashMap::new));
//        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
//        filterChainDefinitionMap.put(shiro.getLogoutUrl(), "logout");
//        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
//        filterChainDefinitionMap.put("/**", "user");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;


        Map<String, String> map = new HashMap<>();
//        LinkedHashMap<String, String> map;
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //登出
        map.put("/logout", "logout");

        //swagger-ui   下面三个都需要  缺少一个都不行
        map.put("/swagger**/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");


        //登录
        shiroFilterFactoryBean.setLoginUrl("/users/users/login");

        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");

        //对所有用户认证  authc
        map.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;

    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        return authorizationAttributeSourceAdvisor;
    }


}
