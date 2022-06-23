package com.example.zuche.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加一个CORS跨域配置类，在工程下新建config包并添加一个CorsConfig 配置类
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    /**
     * @Param:
     * @return:
     * @Author: chengzhang
     * @Date: 2021/12/23
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域访问的路径
        registry.addMapping("/**")
                //允许跨域访问的源  跨域配置报错，将.allowedOrigins替换成.allowedOriginPatterns即可。
//                .allowedOriginPatterns("*")
                //允许请求方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //预检间隔时间
                .maxAge(168000)
                //允许头部设置
                .allowedHeaders("*")
                //是否发送cookie
                .allowCredentials(true);
    }
}
