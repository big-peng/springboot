package com.sippr.demo.modules.test.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 趋势数据
 * </p>
 *
 * @author ChenXiangpeng
 * @since 2020-09-29
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TrendData implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "device_name";
    public static final String MEAS_DATE = "meas_date";
    public static final String MEAS_VALUE = "meas_value";
    public static final String CREATE_TIME = "create_time";
    public static final String LAST_UPDATE_TIME = "last_update_time";

    private Integer id;

    /**
     * 设备传感器id
     */
    private String deviceId;

    /**
     * 设备传感器名称
     */
    private String deviceName;

    /**
     * 测量时间
     */
    private LocalDateTime measDate;

    /**
     * 测量数据值
     */
    private String measValue;

    /**
     * 测量数据单位
     */
    private String measUnit;

    /**
     * 波形数据key 唯一标识
     */
    private String waveKey;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上次更新时间
     */
    private LocalDateTime lastUpdateTime;
}
