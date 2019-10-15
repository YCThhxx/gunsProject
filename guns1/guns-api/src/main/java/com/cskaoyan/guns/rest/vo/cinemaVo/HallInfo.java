package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallInfo implements Serializable {

    String discountPrice;

    Integer hallFieldId;

    String hallName;

    int price;

    String seatFile;

    String soldSeats;
}
