package com.sippr.demo.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回格式
 * @author ChenXiangpeng
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {
    private String code;
    private String message;
    private T data;

    public static <T> ApiResult<T> success(){
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T> ApiResult<T> success(T data){
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> ApiResult<T> fail(String code, String msg){
        return new ApiResult<>(code, msg, null);
    }

    public static <T> ApiResult<T> resultCode(ResultCode resultCode){
        return new ApiResult<>(resultCode.getCode(), resultCode.getMsg(), null);
    }
}
