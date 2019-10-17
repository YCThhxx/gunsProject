package com.cskaoyan.guns.rest.vo.orderVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRespVo<T> implements Serializable {

    private static final long serialVersionUID = 8736832156637997325L;
    private T data;
    private int status;
    private String msg;

    public OrderRespVo(T data, int status, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }
}
