package com.sippr.demo.modules.mongo.dao;

import com.sippr.demo.modules.mongo.entity.TestPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ChenXiangpeng
 */
@Component
public class MongoDbDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TestPo> getTest(){
        Query query = new Query(Criteria.where("username").is("123"));
        return mongoTemplate.find(query, TestPo.class);
    }


    public void insert(){
        TestPo insert = mongoTemplate.insert(TestPo.builder().username("admin").build());
        System.out.println(insert);
    }
}
