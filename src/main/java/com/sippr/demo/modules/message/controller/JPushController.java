package com.sippr.demo.modules.message.controller;

import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.modules.message.service.JPushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 极光推送
 * @author ChenXiangpeng
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class JPushController {
    @Autowired
    private JPushMessageService jPushMessageService;

    @GetMapping("/send/all")
    public ApiResult<Object> sendMsgToAll(@RequestParam("msg") String msg){
        jPushMessageService.sendMessageToAll(msg);
        return ApiResult.success();
    }

    @GetMapping("/send/person")
    public ApiResult<Object> sendMsgToPerson(@RequestParam("msg") String msg, @RequestParam("personId") Integer personId){
        jPushMessageService.sendMessageToPerson(msg, personId);
        return ApiResult.success();
    }
}
