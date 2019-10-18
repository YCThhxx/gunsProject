package com.cskaoyan.guns.rest.modular.pay.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cskaoyan.guns.rest.AliPayMain;
import com.cskaoyan.guns.rest.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = PayService.class)
public class PayServiceImpl implements PayService {

    @Autowired
    AliPayMain aliPayMain;



    @Override
    public Double getOrderById(String orderId) {
        //查询订单详情的语句
        return 120.0;
    }

    @Override
    public boolean getPayInfo(String orderId) {
        //查询订单详情语句
        //?把订单信息写入方法里面
        Double orderPrice = null;
        Double filmPrice = null;
        Integer cinemaId = 1;
        Integer filmId = 1;
        Integer num = 1;
        boolean flag = aliPayMain.test_trade_precreate(orderId,orderPrice,filmPrice,cinemaId,filmId,num);
        return flag;
    }

    @Override
    public Integer queryOrderStatusById(String orderId) {
        return null;
    }
}
