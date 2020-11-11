package com.sippr.demo.modules.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author ChenXiangpeng
 */
@Component
public class JobDemoTask implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        for (int i=0;i<10;i++){
            try{
                Thread.sleep(10000L);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
