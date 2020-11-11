package com.sippr.demo.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.sippr.demo.modules.test.entity.po.TenantInfo;
import com.sippr.demo.modules.test.mapper.TenantInfoMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenXiangpeng
 */
@Slf4j
@Configuration
public class DynamicDataSourceInit {
    @Autowired
    private TenantInfoMapper tenantInfoMapper;

    @PostConstruct
    public void initDataSource()  {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("=====初始化动态数据源=====");
        DynamicDataSource dynamicDataSource = (DynamicDataSource)ApplicationContextProvider.getBean("dynamicDataSource");
        HikariDataSource master = (HikariDataSource)ApplicationContextProvider.getBean("master");
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);

        List<TenantInfo> tenantList = tenantInfoMapper.selectList(new QueryWrapper<>());
        for (TenantInfo tenantInfo : tenantList) {
            log.info(tenantInfo.toString());
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/"+ tenantInfo.getTenantName() +"?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setDataSourceProperties(master.getDataSourceProperties());
            dataSourceMap.put(tenantInfo.getTenantName(), dataSource);
        }
        // 设置数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        // 必须执行此操作，才会重新初始化AbstractRoutingDataSource中的resolvedDataSources，也只有这样，动态切换才会起效
        dynamicDataSource.afterPropertiesSet();
    }
}
