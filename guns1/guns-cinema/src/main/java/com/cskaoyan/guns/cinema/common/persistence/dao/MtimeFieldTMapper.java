package com.cskaoyan.guns.cinema.common.persistence.dao;

import com.cskaoyan.guns.cinema.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cskaoyan.guns.rest.vo.cinemaVo.HallInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    List<MtimeFieldT> getFiledInfo(@Param("id") Integer cinemaId);

    List<MtimeFieldT> getFilmFields(@Param("cinemaId") Integer cinemaId,
                                    @Param("filmId") Integer filmId);

    HallInfo getFiledByFiledId(@Param("id") Integer fieldId);
}
