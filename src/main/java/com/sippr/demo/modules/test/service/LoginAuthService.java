package com.sippr.demo.modules.test.service;

import com.sippr.demo.common.entity.AuthToken;

/**
 * @author ChenXiangpeng
 */
public interface LoginAuthService {
    /**
     * 登录认证
     * @param username 用户名
     * @param password 密码
     * @return java.lang.String
     */
    AuthToken userAuth(String username, String password);
}
