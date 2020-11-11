package com.sippr.demo.modules.job.utils;

import com.sippr.demo.modules.job.enums.TaskType;
import org.quartz.*;

/**
 * @author ChenXiangpeng
 */
public class ScheduleUtil {
    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    public static void createTask(Scheduler scheduler, String taskImplType, String triggerType) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TaskType.valueOfByTaskType(taskImplType).getTaskImpl()).withIdentity(getJobKey(12L))
                //JobDataMap可以给任务传递参数
                .usingJobData("jobKey", "job_param1")
                .build();
        // 创建Trigger触发器设置使用cronSchedule方式调度
        Trigger trigger;
        if ("cron".equals(triggerType)){
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(12L))
                    .usingJobData("job_trigger_param","job_trigger_param1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();
        }else{
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(12L))
                    .usingJobData("job_trigger_param","job_trigger_param1")
                    .startNow()
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? 2018"))
                    .build();
        }

        // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
