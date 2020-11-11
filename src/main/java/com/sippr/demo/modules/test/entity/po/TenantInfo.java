package com.sippr.demo.modules.test.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenXiangpeng
 * @since 2020-10-20
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    @TableField("TENANT_ID")
    private String tenantId;

    /**
     * 租户名称
     */
    @TableField("TENANT_NAME")
    private String tenantName;

    /**
     * 数据源url
     */
    @TableField("DATASOURCE_URL")
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    @TableField("DATASOURCE_USERNAME")
    private String datasourceUsername;

    /**
     * 数据源密码
     */
    @TableField("DATASOURCE_PASSWORD")
    private String datasourcePassword;

    /**
     * 数据源驱动
     */
    @TableField("DATASOURCE_DRIVER")
    private String datasourceDriver;

    /**
     * 系统账号
     */
    @TableField("SYSTEM_ACCOUNT")
    private String systemAccount;

    /**
     * 账号密码
     */
    @TableField("SYSTEM_PASSWORD")
    private String systemPassword;

    /**
     * 系统PROJECT
     */
    @TableField("SYSTEM_PROJECT")
    private String systemProject;

    /**
     * 是否启用（1是0否）
     */
    @TableField("STATUS")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String TENANT_ID = "TENANT_ID";

    public static final String TENANT_NAME = "TENANT_NAME";

    public static final String DATASOURCE_URL = "DATASOURCE_URL";

    public static final String DATASOURCE_USERNAME = "DATASOURCE_USERNAME";

    public static final String DATASOURCE_PASSWORD = "DATASOURCE_PASSWORD";

    public static final String DATASOURCE_DRIVER = "DATASOURCE_DRIVER";

    public static final String SYSTEM_ACCOUNT = "SYSTEM_ACCOUNT";

    public static final String SYSTEM_PASSWORD = "SYSTEM_PASSWORD";

    public static final String SYSTEM_PROJECT = "SYSTEM_PROJECT";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

}
