package com.sippr.demo.modules.message.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.sippr.demo.modules.message.service.JPushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author van
 */
@Slf4j
@Service
public class JPushMessageServiceImpl implements JPushMessageService {
    @Value("${JPush.appKey}")
    private String appKey;

    @Value("${JPush.master.secret}")
    private String appSecret;

    @Value("${JPush.application.name}")
    private String appName;

    @Override
    public void sendMessageToAll(String msg) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jPushClient = new JPushClient(appSecret, appKey, null, clientConfig);
        PushPayload sippr = PushPayload.alertAll(msg);
        try {
            PushResult pushResult = jPushClient.sendPush(sippr);
            log.info("send all success, msg: {} , result: {}", msg, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("send to all error , msg: {}, e: {}", msg, e.getMessage());
        }
    }

    @Override
    public void sendMessageToPerson(String msg, Integer personId) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jPushClient = new JPushClient(appSecret, appKey, null, clientConfig);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(String.valueOf(personId)))
                .setNotification(Notification.alert(msg)).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("send person: {} success, msg: {},  result: {}", personId, msg, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("send to person error, personID: {}, msg: {}, e: {}", personId, msg, e.getMessage());
        }
    }

    @Override
    public void sendMessageToPersons(String msg, List<String> personIds) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jPushClient = new JPushClient(appSecret, appKey, null, clientConfig);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(personIds))
                .setNotification(Notification.alert(msg)).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("send to persons: {} success, msg: {} , result: {}", personIds, msg, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("send to persons error, personIDS: {}, msg: {}, e :{}", personIds, msg, e.getMessage());
        }
    }

    @Override
    public void sendMessageToGroup(String msg, List<String> tags) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jPushClient = new JPushClient(appSecret, appKey, null, clientConfig);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.alert(msg)).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("send to group success, tags: {} , result: {}", tags, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("send to group error, tags: {}, msg: {}, e :{}", tags, msg, e.getMessage());
        }
    }
}
