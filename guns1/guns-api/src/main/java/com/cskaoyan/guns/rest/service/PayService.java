package com.cskaoyan.guns.rest.service;

public interface PayService {

    Double getOrderById(String orderId);

    boolean getPayInfo(String orderId);

    Integer queryOrderStatusById(String orderId);
}
