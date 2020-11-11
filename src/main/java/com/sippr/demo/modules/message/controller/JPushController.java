package com.sippr.demo.modules.message.controller;

import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.modules.message.service.JPushMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 极光推送
 * @author ChenXiangpeng
 */
@RestController
@RequestMapping("/message")
@Api(tags = "推送")
@Slf4j
public class JPushController {
    @Autowired
    private JPushMessageService jPushMessageService;

    @GetMapping("/send/all")
    @ApiOperation("全部")
    public ApiResult<Object> sendMsgToAll(@RequestParam("msg") String msg){
        jPushMessageService.sendMessageToAll(msg);
        return ApiResult.success();
    }

    @GetMapping("/send/person")
    @ApiOperation("人员")
    public ApiResult<Object> sendMsgToPerson(@RequestParam("msg") String msg, @RequestParam("personId") Integer personId){
        jPushMessageService.sendMessageToPerson(msg, personId);
        return ApiResult.success();
    }
}
