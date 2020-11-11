package com.sippr.demo.common.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;

/**
 * @author ChenXiangpeng
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Hello, Job" + LocalDateTime.now() + jobExecutionContext.getJobDetail().getKey());
    }
}
