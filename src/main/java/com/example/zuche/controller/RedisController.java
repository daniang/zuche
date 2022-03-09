package com.example.zuche.controller;

import com.example.zuche.users.pojo.Users;
import com.example.zuche.users.service.IUsersService;
import com.example.zuche.utils.RedisDBChangeUtil;
import com.example.zuche.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author : chengzhang
 * @Date : 2022/1/6 16:54
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redis操作")
@Slf4j
public class RedisController {

    private static int ExpireTime = 600;//redis 中存储的过期时间 60s

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private IUsersService usersService;


    @Resource
    private RedisDBChangeUtil redisDBChangeUtil;


    @ApiOperation("存数据")
    @GetMapping("redisSet")
    public boolean redisSet(String key, String value) {
        Users users = usersService.getById(1);

        return redisUtil.set(key, value);
    }

    @ApiOperation("取数据")
    @GetMapping("redisGet")
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

    @ApiOperation("设置缓存key的时间")
    @GetMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }

    /**
     * todo 此方法暂未完成
     * @return
     */
    @PostMapping("/changeDb")
    public String changeDb() {


        redisUtil.set("redis","db0222");
        redisDBChangeUtil.setDataBase(1);
        redisUtil.set("redis","db1222");

        redisDBChangeUtil.setDataBase(2);
        redisUtil.set("redis","db2222");
        return "okok";
    }


}
