package com.cskaoyan.guns.rest.cinema.vo;

import java.io.Serializable;

public class HalltypeList implements Serializable {
    private  int halltypeId;
    private  String halltypeName;
    private  boolean active;

    public HalltypeList() {
    }

    public HalltypeList(int halltypeId, String halltypeName, boolean active) {
        this.halltypeId = halltypeId;
        this.halltypeName = halltypeName;
        this.active = active;
    }

    @Override
    public String toString() {
        return "HalltypeList{" +
                "halltypeId=" + halltypeId +
                ", halltypeName='" + halltypeName + '\'' +
                ", active=" + active +
                '}';
    }

    public int getHalltypeId() {
        return halltypeId;
    }

    public void setHalltypeId(int halltypeId) {
        this.halltypeId = halltypeId;
    }

    public String getHalltypeName() {
        return halltypeName;
    }

    public void setHalltypeName(String halltypeName) {
        this.halltypeName = halltypeName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
