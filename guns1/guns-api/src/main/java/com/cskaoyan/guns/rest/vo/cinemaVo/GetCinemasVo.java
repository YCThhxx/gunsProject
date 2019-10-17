package com.cskaoyan.guns.rest.vo.cinemaVo;

import java.io.Serializable;

public class GetCinemasVo implements Serializable {
    private  int uuid;
    private  String cinemaName;
    private  String address;
    private  int minimumPrice;

    @Override
    public String toString() {
        return "GetCinemasVo{" +
                "uuid=" + uuid +
                ", cinemaName='" + cinemaName + '\'' +
                ", address='" + address + '\'' +
                ", minimumPrice=" + minimumPrice +
                '}';
    }

    public GetCinemasVo() {
    }

    public GetCinemasVo(int uuid, String cinemaName, String address, int minimumPrice) {
        this.uuid = uuid;
        this.cinemaName = cinemaName;
        this.address = address;
        this.minimumPrice = minimumPrice;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}
