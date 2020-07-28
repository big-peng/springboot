package com.sippr.demo.modules.test.service.impl;

import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.modules.test.entity.dto.UserDTO;
import com.sippr.demo.modules.test.dao.UserDao;
import com.sippr.demo.modules.test.entity.po.User;
import com.sippr.demo.modules.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ChenXiangpeng
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO getUser(String username){
        Optional<User> userOptional =  userDao.selectByUsername(username);
        if (!userOptional.isPresent()){
            throw new CommonException("用户名不存在");
        }
        return UserDTO.of(userOptional.get());
    }
}
