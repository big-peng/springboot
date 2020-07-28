package com.sippr.demo.modules.message.service;

import java.util.List;

/**
 * @author van
 */
public interface JPushMessageService {

    /**
     * 发送消息给所有平台所有人
     *
     * @param msg message
     */
    void sendMessageToAll(String msg);

    /**
     * 通过personId去单推数据，但是不会在log表记录消息信息，主要用在直接使用推送的场景
     * @param msg msg
     * @param personId personId
     */
    void sendMessageToPerson(String msg, Integer personId);

    /**
     * 给指定的persons发送一条提示信息
     * personId 为对应的用户标签
     *
     * @param msg message
     * @param personIds personId
     */
    void sendMessageToPersons(String msg, List<String> personIds);

    /**
     * 根据组信息进行推送
     *
     * @param msg message
     * @param tags tags 分组标签数据
     */
    void sendMessageToGroup(String msg, List<String> tags);
}
