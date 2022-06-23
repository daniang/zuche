package com.example.zuche.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @desc :  TODO
 * @Author : chengzhang
 * @Date : 2022/6/23 10:37
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class ConfigProperties {


    private String hostName;

    private int  port;

    private String from;



}
