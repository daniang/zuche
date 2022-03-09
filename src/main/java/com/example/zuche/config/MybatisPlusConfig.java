package com.example.zuche.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc :  Mybatis-Plus 来实现分页查询   用ipage来分页 需要配置这个 。
 * @Author : chengzhang
 * @Date : 2022/1/19 14:17
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //乐观锁实现方式：
        //取出记录时，获取当前 version
        //更新时，带上这个 version
        //执行更新时， set version = newVersion where version = oldVersion
        //如果 version 不对，就更新失败
        //在实体类的字段加上 @Version注解 。
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;

    }

//    public ConfigurationCustomizer configurationCustomizer () {
//
//        return configuration -> configuration.setUse
//    }


}
