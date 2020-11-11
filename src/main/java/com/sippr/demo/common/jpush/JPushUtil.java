package com.sippr.demo.common.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ChenXiangpeng
 */
@Component
@Slf4j
public class JPushUtil {
    /**
     * 根据别名推送通知消息，别名可对应用户id
     * @param jPushBean 推送消息数据
     * @param alias 别名数组，不传则全部
     */
    public void sendPushByAlias(JPushBean jPushBean, List<String> alias){
        JPushClient jPushClient = JPushClientInstance.getInstance();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder().setAlert(jPushBean.getContent())
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(jPushBean.getTitle()).addExtras(jPushBean.getExtrasMap()).build()).build()).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("通知:{}已发送至:{},结果: {}", jPushBean, alias, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("通知发送至{}失败,msg:{},e:{}",alias, jPushBean, e.getMessage());
        }
    }

    /**
     * 重载方法
     * @param jPushBean 推送消息数据
     * @param alias 别名数组，不传则全部
     */
    public void sendPushByAlias(JPushBean jPushBean, String... alias){
        sendPushByAlias(jPushBean, Lists.newArrayList(alias));
    }

    /**
     * 根据分组推送消息，可以是部门之类的
     * @param jPushBean 推送消息数据
     * @param tags 分组数组，空则推送全部
     */
    public void sendPushByTags(JPushBean jPushBean, List<String> tags){
        JPushClient jPushClient = JPushClientInstance.getInstance();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(CollectionUtils.isEmpty(tags) ? Audience.all() : Audience.tag(tags))
                .setNotification(Notification.newBuilder().setAlert(jPushBean.getContent())
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(jPushBean.getTitle()).addExtras(jPushBean.getExtrasMap()).build()).build()).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("通知:{}已发送至:{},结果: {}", jPushBean, tags, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("通知发送至{}失败,msg:{},e:{}", tags, jPushBean, e.getMessage());
        }
    }

    /**
     * 重载方法
     * @param jPushBean 推送消息数据
     * @param tags 分组数组，空则推送全部
     */
    public void sendPushByTags(JPushBean jPushBean, String... tags){
        sendPushByAlias(jPushBean, Lists.newArrayList(tags));
    }

    /**
     * 直接发送消息，不在通知栏展示，可用于实时数据展示
     * @param message 消息体
     * @param alias 别名，同上
     */
    public void sendMessageByAlias(MessageBean message, List<String> alias){
        JPushClient jPushClient = JPushClientInstance.getTimeoutInstance();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                .setMessage(Message.newBuilder().setMsgContent(message.toString()).build()).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("消息:{}已发送至:{},结果: {}", message, alias, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("消息发送至{}失败,msg:{},e:{}", alias, message, e.getMessage());
        }
    }

    /**
     * 重载方法
     * @param message 消息体
     * @param alias 别名，同上
     */
    public void sendMessageByAlias(MessageBean message, String... alias){
        sendMessageByAlias(message,Lists.newArrayList(alias));
    }

    /**
     * 直接发送消息，不在通知栏展示，可用于实时数据展示
     * @param message 消息体
     * @param tags 分组，同上
     */
    public void sendMessageByTags(MessageBean message, List<String> tags){
        JPushClient jPushClient = JPushClientInstance.getTimeoutInstance();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(CollectionUtils.isEmpty(tags) ? Audience.all() : Audience.tag(tags))
                .setMessage(Message.newBuilder().setMsgContent(message.toString()).build()).build();
        try {
            PushResult pushResult = jPushClient.sendPush(payload);
            log.info("消息:{}已发送至:{},结果: {}", message, tags, pushResult);
        } catch (APIConnectionException | APIRequestException e) {
            log.error("消息发送至{}失败,msg:{},e:{}", tags, message, e.getMessage());
        }
    }

    /**
     * 重载方法
     * @param message 消息体
     * @param tags 分组，同上
     */
    public void sendMessageByTags(MessageBean message, String... tags){
        sendMessageByTags(message, Lists.newArrayList(tags));
    }
}
