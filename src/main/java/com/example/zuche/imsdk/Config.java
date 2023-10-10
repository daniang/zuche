package com.example.zuche.imsdk;


import com.easemob.im.server.EMProperties;
import com.easemob.im.server.EMService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public EMService service() {

        EMProperties properties = EMProperties.builder()
                .setAppkey("1162171120178658#huanxinstudy")
                .setClientId("YXA6Spi-wM3SEeeZgnG9GYaciw")
                .setClientSecret("YXA6eBR645a-FXX-3WgRaPZ4eewURbU")
                .build();

        return new EMService(properties);
    }
}
