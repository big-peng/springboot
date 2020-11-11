package com.sippr.demo.common.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.connection.HttpProxy;
import cn.jpush.api.JPushClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JPushClientInstance
 * @author ChenXiangpeng
 */
@Component
public class JPushClientInstance {
    private static String appKey;
    private static String appSecret;
    /**
     * 代理服务器ip
     */
    private static String proxyHost;

    @Value("${JPush.appKey}")
    public void setAppKey(String appKey) {
        JPushClientInstance.appKey = appKey;
    }

    @Value("${JPush.master.secret}")
    public void setAppSecret(String appSecret) {
        JPushClientInstance.appSecret = appSecret;
    }

    @Value("${JPush.proxyHost:false}")
    public void setProxyHost(String proxyHost) {
        JPushClientInstance.proxyHost = proxyHost;
    }

    private JPushClientInstance() {}

    private static JPushClient jPushClient;
    private static JPushClient jPushClientTimeout;

    public static JPushClient getInstance(){
        if (jPushClient == null){
            ClientConfig clientConfig = ClientConfig.getInstance();
            HttpProxy httpProxy = null;
            if (!"false".equals(proxyHost)){
                clientConfig.setPushHostName("http://"+proxyHost);
                httpProxy = new HttpProxy(proxyHost,446);
            }
            jPushClient = new JPushClient(appSecret, appKey, httpProxy, clientConfig);
        }
        return jPushClient;
    }

    public static JPushClient getTimeoutInstance(){
        if (jPushClientTimeout == null){
            ClientConfig clientConfig = ClientConfig.getInstance();
            //自定义超时时间
            clientConfig.setConnectionTimeout(1500);
            clientConfig.setReadTimeout(1500);
            clientConfig.setTimeToLive(2000L);
            clientConfig.setConnectionRequestTimeout(1500);
            clientConfig.setSocketTimeout(2000);

            HttpProxy httpProxy = null;
            if (!"false".equals(proxyHost)){
                clientConfig.setPushHostName("http://"+proxyHost);
                httpProxy = new HttpProxy(proxyHost,446);
            }
            jPushClientTimeout = new JPushClient(appSecret, appKey, httpProxy, clientConfig);
        }
        return jPushClientTimeout;
    }
}
