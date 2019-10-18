package com.cskaoyan.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.rest.service.OrderService;
import com.cskaoyan.guns.rest.vo.filmVo.BaseFailRespVo;
import com.cskaoyan.guns.rest.vo.orderVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference(interfaceClass = OrderService.class)
    OrderService orderService;

    @Autowired
    Jedis jedis;

    @PostMapping("buyTickets")
    public OrderResponseVo buyTickets(OrderRequestVo requestVo, HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String uuid1 = jedis.get(token);
        OrderResponseVo responseVo = new OrderResponseVo();
        try {
            TicketVo ticketVo = orderService.getTicketDetail(requestVo, uuid1);
            responseVo.setData(ticketVo);
            responseVo.setStatus(0);
            return responseVo;
        } catch (Exception e) {
            responseVo.setStatus(1);
            responseVo.setMsg("该订单不存在");
            return responseVo;
        }
    }

    @PostMapping("getOrderInfo")
    public Object getOrderInfo(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String uuid = jedis.get(token);
        if (uuid == null) {
            return BaseFailRespVo.fail(2, "你的登录信息已过期，请重新登陆!");
        }
        List<OrderInfoVo> orderInfo = orderService.getOrderInfo(Integer.parseInt(uuid),
                Integer.parseInt(request.getParameter("nowPage")),Integer.parseInt(request.getParameter("pageSize")));
        if (orderInfo.size() == 0) {
            return BaseFailRespVo.fail(1, "订单列表为空哦！~");
        }
        return new OrderRespVo(orderInfo,0,"");
    }
}
