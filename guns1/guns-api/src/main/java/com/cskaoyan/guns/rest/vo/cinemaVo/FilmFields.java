package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;


@Data
public class FilmFields implements Serializable {


    String beginTime;

    String endTime;

    Integer fieldId;

    String hallName;

    String language;

    Integer price;

}
