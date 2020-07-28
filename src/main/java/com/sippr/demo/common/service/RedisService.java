package com.sippr.demo.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis服务类
 * @author ChenXiangpeng
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;

    /**
     * 默认保存时间，该工具类中所有时间默认单位为秒
     */
    private static final long DEFAULT_CACHE_TIME = 2 * 60 * 60;

    /**
     * 将 key，value 存放到redis数据库中，过期时间设置为默认
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        this.set(key, value, DEFAULT_CACHE_TIME);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     *
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间（秒）
     */
    public void set(String key, Object value, long expireTime) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,this.toJsonString(value),expireTime, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key 键
     * @return key键的值是否存在
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取 key 对应的字符串
     * @param key 键
     * @return key键对应的值
     */
    public <T> T get(String key,Class<T> tClass) {
        return toJavaObject(valueOperations.get(key),tClass);
    }

    /**
     * 获得 key 对应的键值，并更新缓存时间，时间长度为默认值
     * @param key 键
     * @return key键对应的值
     */
    public <T> T getAndUpdateTime(String key,Class<T> tClass) {
        T result = this.get(key,tClass);
        if (result != null) {
            this.set(key, result);
        }
        return result;
    }

    /**
     * 更新缓存时间，时间长度为默认值
     * @param key 键
     */
    public void refresh(String key){
        Object result = this.get(key,Object.class);
        if (result != null) {
            this.set(key, result);
        }
    }

    /**
     * 删除 key 对应的 value
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * java对象转为jsonString
     * @param value Object类
     * @return java.lang.String jsonString
     */
    public String toJsonString(Object value){
        return JSONObject.toJSONString(value);
    }

    /**
     * jsonString转为java对象
     * @param jsonString jsonString
     * @param tClass javaClass
     * @return T 指定的java对象
     */
    public <T> T toJavaObject(String jsonString,Class<T> tClass){
        return JSON.parseObject(jsonString,tClass);
    }
}
