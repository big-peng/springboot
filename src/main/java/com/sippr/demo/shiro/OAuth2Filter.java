package com.sippr.demo.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.common.result.ResultCode;
import com.sippr.demo.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2过滤器
 *
 * @author Chenxiangpeng
 */
@Slf4j
public class OAuth2Filter extends BasicHttpAuthenticationFilter {

    private static final String HEADER_TOKEN = "Authorization";

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            String ipAddr = IPUtils.getIpAddr((HttpServletRequest) request);
            log.info("ip:{}",ipAddr);
            log.info("address:{}",((HttpServletRequest) request).getRequestURL());
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("token验证失败：{}",e.getMessage());
            responseError(response,"token验证失败,"+e.getMessage());
            return false;
        }
    }

    /**
     * 登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(HEADER_TOKEN);

        OAuth2Token oAuth2Token = new OAuth2Token(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获,具体的验证逻辑为securityManager中设置的realm类
        getSubject(request, response).login(oAuth2Token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * Shiro的异常处理
     * @param response response
     * @param msg 错误信息
     */
    private void responseError(ServletResponse response,String msg){
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        try {
            String rj = new ObjectMapper().writeValueAsString(ApiResult.fail(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "invalid token"));
            httpResponse.getWriter().append(rj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
