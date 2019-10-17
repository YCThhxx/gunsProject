package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.vo.orderVo.OrderRequestVo;
import com.cskaoyan.guns.rest.vo.orderVo.TicketVo;

public interface OrderService {
    TicketVo getTicketDetail(OrderRequestVo requestVo, String uuid1) throws Exception;
}
