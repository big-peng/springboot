package com.sippr.demo.modules.test.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sippr.demo.modules.test.dao.UserDao;
import com.sippr.demo.modules.test.entity.po.User;
import com.sippr.demo.modules.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ChenXiangpeng
 */
@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(users)){
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }
}
