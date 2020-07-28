package com.sippr.demo.modules.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sippr.demo.common.annotaion.Dao;
import com.sippr.demo.modules.test.entity.po.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChenXiangpeng
 * @since 2020-07-09
 */
@Dao
public interface UserMapper extends BaseMapper<User> {

}
