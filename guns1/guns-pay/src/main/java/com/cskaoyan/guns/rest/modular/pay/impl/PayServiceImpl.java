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
        boolean flag = aliPayMain.test_trade_precreate();
        return flag;
    }

    @Override
    public Integer queryOrderStatusById(String orderId) {
        return null;
    }
}
