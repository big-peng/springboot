package com.sippr.demo.modules.test.service;

import com.sippr.demo.modules.test.entity.dto.UserDTO;

/**
 * @author ChenXiangpeng
 */
public interface UserService {
    /**
     * 根据username获取user信息
     * @param username username
     * @return com.sippr.demo.common.entity.dto.UserDTO
     */
    UserDTO getUser(String username);
}
