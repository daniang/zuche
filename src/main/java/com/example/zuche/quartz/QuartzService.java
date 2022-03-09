package com.example.zuche.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/2/24 9:45
 */
@Component
@Slf4j
public class QuartzService implements InitializingBean {


    private final String CRON_TIME = "* 5 * * * ?";

    private final String TRIGGER_KEY_NAME = "00000000001";

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

//    @PostConstruct
//    public void taskInit() {
//        log.info("=================系统初始化加载定时任务开始===========================");
//        try {
//            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//            Scheduler scheduler = schedulerFactory.getScheduler();
//            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_KEY_NAME, Scheduler.DEFAULT_GROUP);
//            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withDescription("quartz测试定制化定时任务").withIdentity(TRIGGER_KEY_NAME, Scheduler.DEFAULT_GROUP).build();
//
//            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_TIME);
//
//            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
//            scheduler.scheduleJob(jobDetail, cronTrigger);
//            scheduler.start();
//            log.info("================初始化定时任务加载完成==================");
//        } catch (Exception e) {
//            log.info("======================初始化加载定时任务失败====================={}", e);
//            log.error("失败原因{}", e.getMessage());
//        }
//    }
    @Override
    public void afterPropertiesSet()  {
        log.info("=================系统初始化加载定时任务开始=====================");
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_KEY_NAME, Scheduler.DEFAULT_GROUP);
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withDescription("quartz测试定制化定时任务").withIdentity(TRIGGER_KEY_NAME, Scheduler.DEFAULT_GROUP).build();

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_TIME);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
//            scheduler.start();
        } catch (Exception e) {
            log.info("======================初始化加载定时任务失败====================={}", e);
            log.error("失败原因{}", e.getMessage());
        }

    }
}
