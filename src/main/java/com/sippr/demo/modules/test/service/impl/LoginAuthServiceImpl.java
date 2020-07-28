package com.sippr.demo.modules.test.service.impl;

import com.sippr.demo.common.entity.AuthToken;
import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.modules.test.entity.dto.UserDTO;
import com.sippr.demo.modules.test.service.LoginAuthService;
import com.sippr.demo.modules.test.service.UserService;
import com.sippr.demo.shiro.RedisTokenProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenXiangpeng
 */
@Service
@Slf4j
public class LoginAuthServiceImpl implements LoginAuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTokenProcess redisTokenProcess;

    @Override
    public AuthToken userAuth(String username, String password) {
        UserDTO user = userService.getUser(username);
        if (!user.getPassword().equals(password)){
            throw new CommonException("密码错误");
        }
        return redisTokenProcess.createTokenSaveRedis(username);
    }
}
