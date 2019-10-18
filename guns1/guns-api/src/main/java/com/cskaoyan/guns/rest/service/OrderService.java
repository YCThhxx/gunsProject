package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.vo.orderVo.OrderInfoVo;
import com.cskaoyan.guns.rest.vo.orderVo.OrderRequestVo;
import com.cskaoyan.guns.rest.vo.orderVo.TicketVo;

import java.util.List;

public interface OrderService {
    TicketVo getTicketDetail(OrderRequestVo requestVo, String uuid1) throws Exception;

    List<OrderInfoVo> getOrderInfo(int parseInt, int nowPage, int pageSize);

    String getSoldSeatsByFieldId(Integer fieldId);


    OrderInfoVo getOrderById(String orderId);

    Integer changeOrderStatus(String orderId,Integer status);
}
