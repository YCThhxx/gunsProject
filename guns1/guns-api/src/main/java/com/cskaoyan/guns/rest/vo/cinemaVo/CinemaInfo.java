package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaInfo implements Serializable {

    String cinemaAdress;

    Integer cinemaId;

    String cinemaName;

    String cinemaPhone;

    String imgUrl;
}
