package com.sippr.demo.common.exception.handler;

import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一异常拦截器
 * @author van
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResult<Object> allException(Exception e){
        log.error(String.format("统一异常拦截Exception, errorCode : %s ; errorMessage : %s."
                , ResultCode.SYSTEM_ERROR.getCode(), e.getMessage()), e);
        return ApiResult.fail(ResultCode.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(CommonException.class)
    public ApiResult<Object> allException(CommonException e){
        log.error(String.format("统一异常拦截ApmCommonException errorCode : %s ; errorMessage : %s."
                , e.getCode(), e.getMessage()));
        return ApiResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        log.error("数据校验异常：", methodArgumentNotValidException);
        String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResult.fail(ResultCode.SYSTEM_ERROR.getCode(), message);
    }
}
