package com.sippr.demo.modules.elasticjob.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenXiangpeng
 */
//@Configuration
public class ElasticJobRegistryCenterConfig {
/*
    *//**
     * zookeeper链接字符串 localhost:2181
     *//*
    private  String ZOOKEEPER_CONNECTION_STRING = "localhost:2181" ;

    *//**
     * 定时任务命名空间
     *//*
    private  String JOB_NAMESPACE = "elastic-job-boot-java";

    *//**
     * zk的配置及创建注册中心
     * @return CoordinatorRegistryCenter
     *//*
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter setUpRegistryCenter(){
        //zk的配置
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(ZOOKEEPER_CONNECTION_STRING, JOB_NAMESPACE);

        zookeeperConfiguration.setSessionTimeoutMilliseconds(1000);
        //创建注册中心
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }*/
}

