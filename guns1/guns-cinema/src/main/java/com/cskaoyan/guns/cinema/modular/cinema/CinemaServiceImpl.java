package com.cskaoyan.guns.ciname.modular.cinema;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cskaoyan.guns.rest.cinema.CinemaService;
import com.cskaoyan.guns.rest.cinema.vo.*;
import com.cskaoyan.guns.rest.persistence.dao.*;
import com.cskaoyan.guns.rest.persistence.model.*;
import jdk.management.resource.internal.WrapInstrumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    MtimeCinemaTMapper cinemaTMapper;
    @Autowired
    MtimeAreaDictTMapper mtimeAreaDictTMapper;
    @Autowired
    MtimeBrandDictTMapper mtimeBrandDictTMapper;
    @Autowired
    MtimeHallDictTMapper mtimeHallDictTMapper;
    @Override
    public CinemaVo getCinemas(Integer brandId, Integer hallType, Integer districtId, Integer pageSize, Integer nowPage) {
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
        if (districtId != 99){
            objectEntityWrapper.eq("area_id", districtId);
        }
        if (hallType != 99){
            objectEntityWrapper.like("hall_ids", String.valueOf(hallType));
        }
        List<MtimeCinemaT> mtimeCinemaTS = (List<MtimeCinemaT>) cinemaTMapper.selectPage(page, objectEntityWrapper);
        Integer integer = cinemaTMapper.selectCount(objectEntityWrapper);
        ArrayList<GetCinemasVo> getCinemasVolist = new ArrayList<>();
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            Integer uuid = mtimeCinemaT.getUuid();
            String cinemaName = mtimeCinemaT.getCinemaName();
            String cinemaAddress = mtimeCinemaT.getCinemaAddress();
            Integer minimumPrice = mtimeCinemaT.getMinimumPrice();
            GetCinemasVo getCinemasVo = new GetCinemasVo();
            getCinemasVo.setUuid(uuid);
            getCinemasVo.setCinemaName(cinemaName);
            getCinemasVo.setAddress(cinemaAddress);
            getCinemasVo.setMinimumPrice(minimumPrice);
            getCinemasVolist.add(getCinemasVo);
        }
        CinemaVo ok = CinemaVo.ok(getCinemasVolist);
        ok.setNoePage(nowPage);
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
}
