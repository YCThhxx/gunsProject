package com.cskaoyan.guns.rest.vo.orderVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderResponseVo<T> implements Serializable {
    private static final long serialVersionUID = -8500722388493306120L;
    Integer status;
    String msg;
    T data;
}
