package com.cskaoyan.guns.cinema.modular.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cskaoyan.guns.cinema.common.persistence.dao.*;
import com.cskaoyan.guns.cinema.common.persistence.model.*;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.vo.cinemaVo.*;
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

    @Autowired
    MtimeAreaDictTMapper mtimeAreaDictTMapper;

    @Autowired
    MtimeBrandDictTMapper mtimeBrandDictTMapper;

    @Autowired
    MtimeHallDictTMapper mtimeHallDictTMapper;

    @Override
    public CinemaVo getCinemas(Integer brandId, Integer hallType, Integer areaId, Integer pageSize, Integer nowPage) {
        if (pageSize == null){
            pageSize = 12;
        }
        if (nowPage == null){
            nowPage = 1;
        }
        Page<MtimeCinemaT> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(nowPage);
        EntityWrapper<MtimeCinemaT> objectEntityWrapper = new EntityWrapper<>();
        if (brandId != 99){
            objectEntityWrapper.eq("brand_id", brandId);
        }
        if (areaId != 99){
            objectEntityWrapper.eq("area_id", areaId);
        }
        if (hallType != 99){
            objectEntityWrapper.like("hall_ids", String.valueOf(hallType));
        }
        List<MtimeCinemaT> mtimeCinemaTS = (List<MtimeCinemaT>) mtimeCinemaTMapper.selectPage(page, objectEntityWrapper);
        Integer integer = mtimeCinemaTMapper.selectCount(objectEntityWrapper);
        ArrayList<GetCinemasVo> getCinemasVolist = new ArrayList<>();
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            Integer uuid = mtimeCinemaT.getUuid();
            String cinemaName = mtimeCinemaT.getCinemaName();
            String cinemaAddress = mtimeCinemaT.getCinemaAddress();
            Integer minimumPrice = mtimeCinemaT.getMinimumPrice();
            GetCinemasVo getCinemasVo = new GetCinemasVo();
            getCinemasVo.setUuid(uuid);
            getCinemasVo.setCinemaName(cinemaName);
            getCinemasVo.setCinemaAddress(cinemaAddress);
            getCinemasVo.setMinimumPrice(minimumPrice);
            getCinemasVolist.add(getCinemasVo);
        }
        CinemaVo ok = CinemaVo.ok(getCinemasVolist);
        ok.setNowPage(nowPage);
        ok.setTotalPage(integer);
        return  ok;
    }

    @Override
    public CinemaVo getCondition(Integer brandId, Integer hallType, Integer areaId) {
        EntityWrapper<MtimeAreaDictT> areaDictTWrapper = new EntityWrapper<>();
        EntityWrapper<MtimeBrandDictT> brandDictTWrapper = new EntityWrapper<>();
        EntityWrapper<MtimeHallDictT> hallFilmInfoTWrapper = new EntityWrapper<>();

        if (brandId != 99){
            brandDictTWrapper.eq("UUID", brandId);
        }
        List<MtimeBrandDictT> mtimeBrandDictTS = mtimeBrandDictTMapper.selectList(brandDictTWrapper);
        GetConditionVo getConditionVo = new GetConditionVo();
        ArrayList<BrandListVo> brandListVos = new ArrayList<>();
        for (MtimeBrandDictT mtimeBrandDictT : mtimeBrandDictTS) {
            String showName = mtimeBrandDictT.getShowName();
            Integer uuid = mtimeBrandDictT.getUuid();
            BrandListVo brandListVo = new BrandListVo();
            brandListVo.setBrandId(uuid);
            brandListVo.setBrandName(showName);
            if ( uuid == brandId){
                brandListVo.setActive(true);
            }else {
                brandListVo.setActive(false);
            }
            brandListVos.add(brandListVo);
        }
        getConditionVo.setBrandList(brandListVos);


        if (areaId != 99){
            areaDictTWrapper.eq("UUID", areaId);
        }
        List<MtimeAreaDictT> mtimeAreaDictTS = mtimeAreaDictTMapper.selectList(areaDictTWrapper);
        ArrayList<AreaListVo> areaListVos = new ArrayList<>();
        for (MtimeAreaDictT mtimeAreaDictT : mtimeAreaDictTS) {
            String showName = mtimeAreaDictT.getShowName();
            Integer uuid = mtimeAreaDictT.getUuid();
            AreaListVo areaListVo = new AreaListVo();
            areaListVo.setAreaId(uuid);
            areaListVo.setAreaName(showName);
            if (uuid == areaId){
                areaListVo.setActive(true);
            }else {
                areaListVo.setActive(false);
            }
            areaListVos.add(areaListVo);
        }
        getConditionVo.setAreaList(areaListVos);


        if (hallType != 99){
            hallFilmInfoTWrapper.eq("UUID", hallType);
        }
        List<MtimeHallDictT> mtimeHallDictTS = mtimeHallDictTMapper.selectList(hallFilmInfoTWrapper);
        ArrayList<HalltypeList> objects = new ArrayList<>();
        for (MtimeHallDictT mtimeHallDictT : mtimeHallDictTS) {
            Integer uuid = mtimeHallDictT.getUuid();
            String showName = mtimeHallDictT.getShowName();
            HalltypeList halltypeList = new HalltypeList();
            halltypeList.setHalltypeId(uuid);
            halltypeList.setHalltypeName(showName);
            if (uuid == hallType){
                halltypeList.setActive(true);
            }else {
                halltypeList.setActive(false);
            }
            objects.add(halltypeList);
        }
        getConditionVo.setHalltypeList(objects);


        CinemaVo ok = CinemaVo.ok(getConditionVo);
        return  ok;
    }

    @Override
    public BaseVo getFields(Integer cinemaId) {

        MtimeCinemaT cinemaT = mtimeCinemaTMapper.getCinemaInfos(cinemaId);
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
            for (MtimeFieldT filmField : filmFields) {
                filmField.setFieldId(filmField.getUuid());
            }
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
        MtimeCinemaT cinemaInfo = mtimeCinemaTMapper.getCinemaInfos(cinemaId);
        Integer brandId = cinemaInfo.getBrandId();
//        MtimeBannerT bannerT = mtimeBannerTMapper.getbannerInfo(brandId);
//        baseVo.setImgPre(bannerT.getBannerUrl());
//        cinemaInfo.setCinemaId(cinemaInfo.getUuid());
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

    @Override
    public String getCinemaNameById(Integer id) {
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(id);
        return mtimeCinemaT.getCinemaName();
    }
}
