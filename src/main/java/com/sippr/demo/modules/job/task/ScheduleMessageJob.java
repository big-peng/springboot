package com.sippr.demo.modules.job.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * 发送消息定时任务执行代码
 * @author ChenXiangpeng
 */
@Slf4j
public class ScheduleMessageJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("执行发送消息：{}", LocalDateTime.now());
        String jobKey = (String) jobExecutionContext.getMergedJobDataMap().get("jobKey");
        log.info("发送消息：{}", jobKey);
    }
}
