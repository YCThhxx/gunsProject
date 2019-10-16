package com.cskaoyan.guns.rest.cinema.vo;

import java.io.Serializable;

public class AreaListVo implements Serializable {
    private  int areaId;
    private  String areaName;
    private  boolean active;

    public AreaListVo() {
    }

    @Override
    public String toString() {
        return "AreaListVo{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", active=" + active +
                '}';
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AreaListVo(int areaId, String areaName, boolean active) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.active = active;
    }
}
