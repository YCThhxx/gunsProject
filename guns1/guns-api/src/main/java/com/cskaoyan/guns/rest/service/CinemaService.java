package com.cskaoyan.guns.rest.service;


import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;
import com.cskaoyan.guns.rest.vo.cinemaVo.CinemaVo;

public interface CinemaService {

    CinemaVo getCinemas(Integer brandId, Integer hallType, Integer districtId, Integer pageSize, Integer nowPage);

    CinemaVo getCondition(Integer brandId, Integer hallType, Integer areaId);

    BaseVo getFields(Integer cinemaId);

    BaseVo getFieldInfo(Integer cinemaId, Integer fieldId);

    String getCinemaNameById(Integer id);
}
