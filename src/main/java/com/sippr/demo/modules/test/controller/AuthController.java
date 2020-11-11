package com.sippr.demo.modules.test.controller;

import com.sippr.demo.common.entity.AuthToken;
import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.config.DynamicDataSourceContextHolder;
import com.sippr.demo.modules.test.service.LoginAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChenXiangpeng
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
@Api(tags = "认证")
@Slf4j
public class AuthController {
    @Autowired
    private LoginAuthService loginAuthService;

    @GetMapping("/login")
    @ApiOperation("登录")
    public ApiResult<AuthToken> loginAuth(@RequestParam("username") String username,
                                          @RequestParam("password") String password){
        log.debug("123");
        String dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        log.info("当前数据源：{}", dataSourceKey);
        return ApiResult.success(loginAuthService.userAuth(username, password));
    }
}
