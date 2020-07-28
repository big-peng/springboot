package com.sippr.demo.common.exception;

import com.sippr.demo.common.result.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: ChenXiangpeng
 * @description:
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 8669822979975640792L;
    private String code;

    public CommonException(String message) {
        super(message);
        this.code = ResultCode.SYSTEM_ERROR.getCode();
    }

    public CommonException(String message, String code) {
        super(message);
        this.code = code;
    }

    public CommonException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}
