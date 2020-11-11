package com.sippr.demo.modules.job.enums;

import com.sippr.demo.modules.job.task.ScheduleMessageJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务实现枚举
 * @author ChenXiangpeng
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TaskType {
    /**
     * 消息任务
     */
    MESSAGE_TASK("message", ScheduleMessageJob.class);
    private String taskType;
    private Class<? extends QuartzJobBean> taskImpl;

    public static TaskType valueOfByTaskType(String taskType){
        for (TaskType type : TaskType.values()) {
            if (type.taskType.equals(taskType)){
                return type;
            }
        }
        return MESSAGE_TASK;
    }
}
