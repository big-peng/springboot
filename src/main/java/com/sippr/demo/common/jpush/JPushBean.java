package com.sippr.demo.common.jpush;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息推送实体类
 * @author ChenXiangpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JPushBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 推送标题
     */
    private String title;
    /**
     * 推送内容
     */
    private String content;
    /**
     * 推送目标
     */
    private Integer targetId;
    /**
     * 消息源订单id
     */
    private Integer sourceId;
    /**
     * 额外推送消息，可用于传递数据
     */
    private Map<String,String> extrasMap;
}
