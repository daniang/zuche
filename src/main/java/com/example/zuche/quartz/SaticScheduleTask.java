package com.example.zuche.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/2/24 14:02
 */

@Configuration //1主要用于标记配置类，兼备Component的效果
@EnableScheduling  //2.开启定时任务
@Slf4j
public class SaticScheduleTask {
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如:5秒
//    @Scheduled(fixedRate = 5000)
    private void configureTasks() {
        log.info("执行定时任务");
    }


}
