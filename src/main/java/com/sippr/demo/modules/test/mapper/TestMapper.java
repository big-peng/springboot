package com.sippr.demo.modules.test.mapper;

import com.sippr.demo.common.annotaion.Dao;
import com.sippr.demo.modules.test.entity.po.TrendData;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChenXiangpeng
 * @since 2020-07-09
 */
@Dao
public interface TestMapper {

    List<TrendData> test(String s);
}
