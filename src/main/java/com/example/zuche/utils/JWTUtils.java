package com.example.zuche.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    //设置秘钥，用于加密解密
    private static final String jwtToken = "123456caoqiong!";

    //根据用户id创建token
    public static String createToken(Long userId) {

        //将用户id以map的形式封装，取得时候好取
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) //签发算法，秘钥为jwtToken
                .setClaims(claims)//body数据，要唯一，自省设置
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));//一天的有效时间

        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    //检测token是否好使

    @Test
    public void parse(){
        String token = createToken(12l);
        Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);

        Map<String ,Object> body = (Map<String, Object>) parse.getBody();

        System.out.println(body.get("userId"));
        System.out.println(parse);

    }



}
