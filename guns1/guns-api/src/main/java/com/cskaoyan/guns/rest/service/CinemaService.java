package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;

public interface CinemaService {

    BaseVo getFields(Integer cinemaId);

    BaseVo getFieldInfo(Integer cinemaId, Integer fieldId);
}
