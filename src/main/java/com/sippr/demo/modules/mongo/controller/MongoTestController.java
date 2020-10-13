package com.sippr.demo.modules.mongo.controller;

import com.sippr.demo.common.entity.AuthToken;
import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.modules.mongo.dao.MongoDbDao;
import com.sippr.demo.modules.mongo.entity.TestPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ChenXiangpeng
 */
@RestController
@CrossOrigin
@RequestMapping("/mongo")
@Api(tags = "MongoDB测试")
@Slf4j
public class MongoTestController {
    @Autowired
    private MongoDbDao mongoDbDao;

    @GetMapping("/test")
    @ApiOperation("测试")
    public ApiResult<List<TestPo>> mongoTest(){
        List<TestPo> test = mongoDbDao.getTest();
        return ApiResult.success(test);
    }

    @GetMapping("/test/add")
    @ApiOperation("测试新增")
    public ApiResult<Object> mongoTest1(){
        mongoDbDao.insert();
        return ApiResult.success();
    }

}
