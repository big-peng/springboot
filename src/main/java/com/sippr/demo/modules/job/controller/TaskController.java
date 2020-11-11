package com.sippr.demo.modules.job.controller;

import com.sippr.demo.modules.job.utils.ScheduleUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenXiangpeng
 */
@RestController
@Api(tags = "定时任务")
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private Scheduler scheduler;

    @GetMapping("/create")
    @ApiOperation("创建")
    public void createTask(String taskType, String triggerType) throws SchedulerException {
        ScheduleUtil.createTask(scheduler, taskType, triggerType);
    }
}
