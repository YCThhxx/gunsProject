package com.cskaoyan.guns.rest.vo.cinemaVo;

import java.io.Serializable;
import java.util.List;

public class GetConditionVo implements Serializable {
    private  List<BrandListVo> brandList;
    private  List<AreaListVo> areaList;
    private  List<HalltypeList> halltypeList;

    public GetConditionVo() {
    }

    public List<BrandListVo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListVo> brandList) {
        this.brandList = brandList;
    }

    public List<AreaListVo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListVo> areaList) {
        this.areaList = areaList;
    }

    public List<HalltypeList> getHalltypeList() {
        return halltypeList;
    }

    public void setHalltypeList(List<HalltypeList> halltypeList) {
        this.halltypeList = halltypeList;
    }

    public GetConditionVo(List<BrandListVo> brandList, List<AreaListVo> areaList, List<HalltypeList> halltypeList) {
        this.brandList = brandList;
        this.areaList = areaList;
        this.halltypeList = halltypeList;
    }

    @Override
    public String toString() {
        return "GetConditionVo{" +
                "brandList=" + brandList +
                ", areaList=" + areaList +
                ", halltypeList=" + halltypeList +
                '}';
    }
}
