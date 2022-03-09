package com.example.zuche.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @desc :  实现AuthenticationToken 接口 作为Token 传入Realm的载体
 * @Author : chengzhang
 * @Date : 2022/1/24 10:50
 */
public class ShiroAuthToken implements AuthenticationToken {
    private String token;

    public ShiroAuthToken(String token) {

        this.token = token;
    }


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
