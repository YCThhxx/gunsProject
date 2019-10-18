package com.cskaoyan.guns.rest.modular.pay.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cskaoyan.guns.rest.AliPayMain;
import com.cskaoyan.guns.rest.service.OrderService;
import com.cskaoyan.guns.rest.service.PayService;
import com.cskaoyan.guns.rest.vo.orderVo.OrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = PayService.class)
public class PayServiceImpl implements PayService {

    @Autowired
    AliPayMain aliPayMain;

    @Reference(interfaceClass = OrderService.class)
    OrderService orderService;


    @Override
    public boolean getPayInfo(String orderId) {
        //查询订单详情语句
        OrderInfoVo orderInfo = orderService.getOrderById(orderId);
        //?把订单信息写入方法里面
        String filmName = orderInfo.getFilmName();
        String cinemaName = orderInfo.getCinemaName();
        String fieldTime = orderInfo.getFieldTime();
        String orderPrice = orderInfo.getOrderPrice();
        String orderStatus = orderInfo.getOrderStatus();
        String orderTimestamp = orderInfo.getOrderTimestamp();
        String seatsName = orderInfo.getSeatsName();

        boolean flag = aliPayMain.test_trade_precreate(orderId,filmName,cinemaName,fieldTime,orderPrice,orderTimestamp,seatsName);
        System.out.println(flag);
        return flag;
    }

    @Override
    public Integer queryOrderStatusById(String orderId) {
        OrderInfoVo orderById = orderService.getOrderById(orderId);
        String orderStatus = orderById.getOrderStatus();
        if ("待支付".equals(orderStatus)){
            return 0;
        }else if ("已支付".equals(orderStatus)){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public Integer checkOrderStatusAndChange(String orderId) {
        Integer status = aliPayMain.test_trade_query(orderId);
        Integer flag = orderService.changeOrderStatus(orderId,status);
        return status;
    }
}
