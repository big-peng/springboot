package com.sippr.demo.modules.test.dao;

import com.sippr.demo.modules.test.entity.po.User;

import java.util.Optional;

/**
 * @author ChenXiangpeng
 */
public interface UserDao {
    Optional<User> selectByUsername(String username);
}
