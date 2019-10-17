package com.cskaoyan.guns.rest.vo.cinemaVo;

import java.io.Serializable;

public class GetCinemasVo implements Serializable {
    private  int uuid;
    private  String cinemaName;
    private  String cinemaAddress;
    private  int minimumPrice;

    @Override
    public String toString() {
        return "GetCinemasVo{" +
                "uuid=" + uuid +
                ", cinemaName='" + cinemaName + '\'' +
                ", address='" + cinemaAddress + '\'' +
                ", minimumPrice=" + minimumPrice +
                '}';
    }

    public GetCinemasVo() {
    }

    public GetCinemasVo(int uuid, String cinemaName, String cinemaAddress, int minimumPrice) {
        this.uuid = uuid;
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
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

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}
