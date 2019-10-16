package com.cskaoyan.guns.cinema.common.persistence.dao;

import com.cskaoyan.guns.cinema.common.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */
public interface MtimeCinemaTMapper extends BaseMapper<MtimeCinemaT> {

    MtimeCinemaT getCinemaInfo(@Param("id") Integer cinemaId);
}
