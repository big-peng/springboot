package com.sippr.demo.common.result;

/**
 * @author xxs
 * 返回代号
 */
public enum ResultCode {
    /**
     * every fail download fail
     */
    DOWNLOAD_FAIL("download.fail", "下载失败"),

    /**
     * drop something fail
     */
    DROP_FAIL("drop.fail", "删除操作失败"),

    /**
     * result fail
     */
    FAIL("500", "fail"),

    /**
     * login fail
     */
    LOGIN_FAIL("login.fail", "登录失败"),

    /**
     * not found 资源未找到
     */
    NOT_FOUND("404", "not found"),

    /**
     * un auth 未认证
     */
    UN_AUTH("401", "未认证"),

    /**
     * result success
     */
    SUCCESS("200", "success"),

    /**
     * error
     */
    SYSTEM_ERROR("system.error", "系统异常"),

    /**
     * every fail upload fail
     */
    UPLOAD_FAIL("upload.fail", "上传失败"),

    /**
     * 短信验证码十分钟内有效,请使用上一条短信验证码
     */
    VERIFICATION_HAS_SENT("verification.has.sent", "短信验证码十分钟内有效,请使用上一条短信验证码");

    private String code;
    private String msg;

    /**
     * constructor
     */
    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCode getEnum(String id) {
        for (ResultCode num : ResultCode.values()) {
            if (num.code.equals(id)) {
                return num;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
