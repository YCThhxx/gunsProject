package com.cskaoyan.guns.rest.cinema;

import com.cskaoyan.guns.rest.cinema.vo.CinemaVo;

public interface CinemaService {
    CinemaVo getCinemas(Integer brandId,Integer  hallType,Integer districtId,Integer pageSize,Integer nowPage);

    CinemaVo getCondition(Integer brandId, Integer hallType, Integer areaId);
}
