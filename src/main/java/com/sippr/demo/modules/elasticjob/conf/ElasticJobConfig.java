package com.sippr.demo.modules.elasticjob.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sippr.demo.modules.elasticjob.job.JobDemoTask;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ChenXiangpeng
 */
//@Configuration
public class ElasticJobConfig {

    /*@Resource
    private DruidDataSource dataSource;

//    @Autowired
//    SimpleJob fileBackupJob;

    @Autowired
    private JobDemoTask jobDemoTask;

//    @Autowired
//    FileBackupJobDataFlow fileBackupJob;

    @Autowired
    CoordinatorRegistryCenter registryCenter;

    *//**
     * 配置任务详细信息
     * @param jobClass 任务执行类
     * @param cron  执行策略
     * @param shardingTotalCount 分片数量
     * @param shardingItemParameters 分片个性化参数
     * @return
     *//*
    private LiteJobConfiguration createJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                        final String cron,
                                                        final int shardingTotalCount,
                                                        final String shardingItemParameters){
        //JobCoreConfigurationBuilder
        JobCoreConfiguration.Builder JobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
        //设置shardingItemParameters
        if(!StringUtils.isEmpty(shardingItemParameters)){
            JobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
        }
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfigurationBuilder.build();
        //创建SimpleJobConfiguration
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());
        //创建LiteJobConfiguration
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true)
                .monitorPort(9888)//设置dump端口
                .build();
        return liteJobConfiguration;
    }

    //创建支持dataFlow类型的作业的配置信息
    private LiteJobConfiguration createFlowJobConfiguration(final Class<? extends ElasticJob> jobClass,
                                                            final String cron,
                                                            final int shardingTotalCount,
                                                            final String shardingItemParameters){
        //JobCoreConfigurationBuilder
        JobCoreConfiguration.Builder JobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
        //设置shardingItemParameters
        if(!StringUtils.isEmpty(shardingItemParameters)){
            JobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
        }
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfigurationBuilder.build();
        // 定义数据流类型任务配置
        DataflowJobConfiguration jobConfig = new DataflowJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName(),true);
        //创建LiteJobConfiguration
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobConfig).overwrite(true)
                // .monitorPort(9888)//设置dump端口
                .build();
        return liteJobConfiguration;
    }
    @Bean(initMethod = "init")
    public SpringJobScheduler initSimpleElasticJob() {
        // 增加任务事件追踪配置
        JobEventConfiguration jobEventConfig = new JobEventRdbConfiguration(dataSource);
        //创建SpringJobScheduler

        SpringJobScheduler springJobScheduler = new SpringJobScheduler(jobDemoTask, registryCenter,
                createJobConfiguration(jobDemoTask.getClass(), "0 * * * * ?", 1, "0=text,1=image,2=radio,3=vedio")
                ,jobEventConfig);
        return springJobScheduler;
    }*/
}
