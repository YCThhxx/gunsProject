package com.cskaoyan.guns.rest.service;

public interface PayService {

    boolean getPayInfo(String orderId);

    Integer queryOrderStatusById(String orderId);

    Integer checkOrderStatusAndChange(String orderId);
}
