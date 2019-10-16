package com.cskaoyan.guns.rest.cinema.vo;

import java.io.Serializable;

public class BrandListVo implements Serializable {
    private  int brandId;
    private  String brandName;
    private  boolean active;

    public BrandListVo() {
    }

    @Override
    public String toString() {
        return "BrandListVo{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", active=" + active +
                '}';
    }

    public BrandListVo(int brandId, String brandName, boolean active) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.active = active;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
