package com.sippr.demo.shiro;

import com.sippr.demo.common.constants.ShiroConstants;
import com.sippr.demo.common.entity.AuthToken;
import com.sippr.demo.common.service.RedisService;
import com.sippr.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 认证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return (AuthorizationInfo)principals;
    }

    /**
     * token登录验证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        if (accessToken == null) {
            log.error("token为空");
            throw new AuthenticationException("token为空");
        }
        // 校验token有效性
        AuthToken loginUser = this.checkUserTokenIsEffect(accessToken);
        return new SimpleAuthenticationInfo(loginUser, accessToken, getName());
    }

    /**
     * 校验token的有效性
     * @param token token
     * @return AuthToken shiro操作Subject时会用到，所以在这返回
     */
    public AuthToken checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效");
        }

        // 校验token是否超时失效
        if (!jwtTokenRefresh(token, username)) {
            throw new AuthenticationException("token失效请重新登录");
        }

        // todo 查询用户信息验证用户状态是否异常

        return AuthToken.builder().username(username).updateTime(LocalDateTime.now()).token(token).build();
    }

    /**
     * JWTToken刷新生命周期 （解决用户一直在线操作，提供Token失效问题）
     * 当该用户这次请求JWTToken值还在生命周期内，则会通过重新PUT的方式k、v都为Token值，缓存中的token值生命周期时间重新计算(这时候k、v值一样)
     * @param token token
     * @param userName userName
     * @return 是否验证成功
     */
    public boolean jwtTokenRefresh(String token, String userName) {
        // 校验token有效性,不使用jwt校验，直接使用redis校验
        if(redisService.exists(ShiroConstants.TOKEN_PREFIX + userName)){
            //刷新token的有效时间
            redisService.refresh(ShiroConstants.TOKEN_PREFIX + userName);
            return true;
        }
        return false;
    }
}
