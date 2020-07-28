package com.sippr.demo.shiro;

import com.sippr.demo.common.constants.ShiroConstants;
import com.sippr.demo.common.service.RedisService;
import com.sippr.demo.common.entity.AuthToken;
import com.sippr.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对Token缓存操作的处理类
 * @author ChenXiangpeng
 */
@Service
public class RedisTokenProcess {
    @Autowired
    private RedisService redisService;

    /**
     * 创建token并存储到Redis，如果已存在说明已经登录过，直接返回已经存在的token，否则就会将该用户的其他登录者顶掉
     * @param username 用户名
     * @return com.sippr.demo.common.entity.AuthToken
     */
    public AuthToken createTokenSaveRedis(String username){
        AuthToken authToken;
        if(redisService.exists(ShiroConstants.TOKEN_PREFIX +username)){
            authToken = redisService.getAndUpdateTime(ShiroConstants.TOKEN_PREFIX + username, AuthToken.class);
        }else{
            authToken = JwtUtil.createToken(username);
            redisService.set(ShiroConstants.TOKEN_PREFIX+username,authToken);
        }
        return authToken;
    }
}
