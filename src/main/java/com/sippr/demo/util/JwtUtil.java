package com.sippr.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.common.entity.AuthToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Jwt工具类
 * @author ChenXiangpeng
 */
@Service
public class JwtUtil {
    /**
     * 密钥盐值，暂时先固定
     */
    private static final String TOKEN_SECRET="com.sippr.demo";
    /**
     * 默认过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            //校验token，校验的方法会涉及到生成token时所添加的过期时间
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 生成token
     * @param username 用户名
     * @return {@link AuthToken}
     */
    public static AuthToken createToken(String username) {
        long currentTime = System.currentTimeMillis();
        String token;
        try {
            token = JWT.create()
                    //存放数据
                    .withClaim("username",username)
                    .withClaim("currentTime",currentTime)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException je) {
            throw new CommonException("token生成失败");
        }
        return AuthToken.builder().token(token).updateTime(LocalDateTime.now()).username(username).build();
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
