package com.sippr.demo.common.jpush;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义消息类
 * @author ChenXiangpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageBean {
    private String content;
    private String name;
}
