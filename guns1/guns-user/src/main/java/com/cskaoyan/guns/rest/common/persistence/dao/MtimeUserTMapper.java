package com.cskaoyan.guns.rest.common.persistence.dao;

import com.cskaoyan.guns.rest.common.persistence.model.MtimeUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-15
 */
public interface MtimeUserTMapper extends BaseMapper<MtimeUserT> {

    String queryIdByUserName(@Param("username")String username);
    String checkPassword(@Param("username")String username);
}
