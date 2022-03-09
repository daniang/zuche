package com.example.zuche.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author : chengzhang
 * @Date : 2022/1/6 17:30
 */
@Component
public class RedisDBChangeUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();

        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);

            this.redisTemplate.setConnectionFactory(connectionFactory);

            connectionFactory.resetConnection();
        }

    }
}
