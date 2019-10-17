package com.cskaoyan.guns.rest.vo.orderVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderInfoVo implements Serializable {

    private static final long serialVersionUID = -4932370918368392243L;
    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private String orderStatus;
    private String orderTimestamp;
}
