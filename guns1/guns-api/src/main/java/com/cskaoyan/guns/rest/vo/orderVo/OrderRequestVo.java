package com.cskaoyan.guns.rest.vo.orderVo;

import lombok.Data;

import java.io.Serializable;
@Data
public class OrderRequestVo implements Serializable {
    private static final long serialVersionUID = -1470168443052546925L;

    Integer fieldId;
    Integer[] soldSeats;
    String[] seatsName;

}
