package com.cskaoyan.guns.cinema.modular.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cskaoyan.guns.cinema.common.persistence.dao.MtimeBannerTMapper;
import com.cskaoyan.guns.cinema.common.persistence.dao.MtimeCinemaTMapper;
import com.cskaoyan.guns.cinema.common.persistence.dao.MtimeFieldTMapper;
import com.cskaoyan.guns.cinema.common.persistence.dao.MtimeHallFilmInfoTMapper;
import com.cskaoyan.guns.cinema.common.persistence.model.MtimeCinemaT;
import com.cskaoyan.guns.cinema.common.persistence.model.MtimeFieldT;
import com.cskaoyan.guns.cinema.common.persistence.model.MtimeHallFilmInfoT;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;
import com.cskaoyan.guns.rest.vo.cinemaVo.CinemaInfo;
import com.cskaoyan.guns.rest.vo.cinemaVo.HallInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;

    @Autowired
    MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Autowired
    MtimeFieldTMapper mtimeFieldTMapper;

    @Autowired
    MtimeBannerTMapper mtimeBannerTMapper;

    @Override
    public BaseVo getFields(Integer cinemaId) {
        MtimeCinemaT cinemaT = mtimeCinemaTMapper.getCinemaInfo(cinemaId);
        //设置cinemaInfo
        CinemaInfo cinemaInfo = new CinemaInfo();
        cinemaInfo.setCinemaAdress(cinemaT.getCinemaAddress());
        cinemaInfo.setCinemaId(cinemaId);
        cinemaInfo.setCinemaName(cinemaT.getCinemaName());
        cinemaInfo.setCinemaPhone(cinemaT.getCinemaPhone());
        cinemaInfo.setImgUrl(cinemaT.getImgAddress());
        //这个电影院有哪些电影
        List<MtimeFieldT> fieldT = mtimeFieldTMapper.getFiledInfo(cinemaId);
        Set<MtimeHallFilmInfoT> filmList = new HashSet<MtimeHallFilmInfoT>();
        for (MtimeFieldT mtimeFieldT : fieldT) {
            Integer filmId = mtimeFieldT.getFilmId();
            MtimeHallFilmInfoT hallInfo = mtimeHallFilmInfoTMapper.getFilmInfo(filmId);
            List<MtimeFieldT> filmFields = mtimeFieldTMapper.getFilmFields(cinemaId,filmId);
            hallInfo.setFilmFields(filmFields);
            filmList.add(hallInfo);
        }
//        Integer brandId = cinemaT.getBrandId();
        //调用查询banner_url
//        MtimeBannerT bannerT = mtimeBannerTMapper.getbannerInfo(brandId);
        BaseVo<Object> baseVo = new BaseVo<>();
//        baseVo.setImgPre(bannerT.getBannerUrl());
        baseVo.setStatus(0);
        Map<String, Object> map = new HashMap<>();
        map.put("cinemaInfo",cinemaInfo);
        map.put("filmList",filmList);
        baseVo.setData(map);
        return baseVo;
    }

    @Override
    public BaseVo getFieldInfo(Integer cinemaId, Integer fieldId) {
        BaseVo<Map> baseVo = new BaseVo<>();
        HashMap<String, Object> map = new HashMap<>();
        MtimeCinemaT cinemaInfo = mtimeCinemaTMapper.getCinemaInfo(cinemaId);
//        Integer brandId = cinemaInfo.getBrandId();
//        MtimeBannerT bannerT = mtimeBannerTMapper.getbannerInfo(brandId);
//        baseVo.setImgPre(bannerT.getBannerUrl());
        cinemaInfo.setCinemaId(cinemaInfo.getUuid());
        map.put("cinemaInfo",cinemaInfo);
        MtimeHallFilmInfoT filmInfo = mtimeHallFilmInfoTMapper.getFilmInfoByFieldId(fieldId);
        filmInfo.setFilmLanguage(filmInfo.getFilmType());
        map.put("filmInfo",filmInfo);
        HallInfo hallInfo = mtimeFieldTMapper.getFiledByFiledId(fieldId);
        map.put("hallInfo",hallInfo);
        baseVo.setStatus(0);
        baseVo.setData(map);
        return baseVo;
    }
}
