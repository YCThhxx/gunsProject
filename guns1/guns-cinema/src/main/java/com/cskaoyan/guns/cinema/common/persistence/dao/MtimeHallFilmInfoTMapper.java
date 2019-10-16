package com.cskaoyan.guns.cinema.common.persistence.dao;

import com.cskaoyan.guns.cinema.common.persistence.model.MtimeHallFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影厅电影信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */
public interface MtimeHallFilmInfoTMapper extends BaseMapper<MtimeHallFilmInfoT> {

    MtimeHallFilmInfoT getFilmInfo(@Param("id") Integer FilmId);

    MtimeHallFilmInfoT getFilmInfoByFieldId(@Param("id") Integer fieldId);
}
