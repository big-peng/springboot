package com.sippr.demo.modules.websocket.controller;

import com.sippr.demo.modules.websocket.service.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChenXiangpeng
 */
@RestController
@CrossOrigin
@RequestMapping("/websocket")
@Api(tags = "websocket测试")
@Slf4j
public class WebTestController {
    @Autowired
    private WebSocket webSocket;

    @GetMapping("/send")
    @ApiOperation("发送消息")
    public void send(@RequestParam String message) throws Exception {
        WebSocket.webSocketSet.forEach(e->{
            try {
                e.sendMessage(message);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
