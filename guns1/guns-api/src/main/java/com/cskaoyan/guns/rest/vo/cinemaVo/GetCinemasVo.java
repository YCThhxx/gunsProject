package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCinemasVo implements Serializable {

    private  int uuid;

    private  String cinemaName;

    private  String cinemaAddress;

    private  int minimumPrice;


}
