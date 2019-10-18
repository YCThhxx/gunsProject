package com.cskaoyan.guns.rest.modular.pay.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.core.exception.GunsException;
import com.cskaoyan.guns.rest.common.exception.BizExceptionEnum;
import com.cskaoyan.guns.rest.service.PayService;
import com.cskaoyan.guns.rest.vo.respVo.PayInfo;
import com.cskaoyan.guns.rest.vo.respVo.PayRespVo;
import com.cskaoyan.guns.rest.vo.respVo.userInfo.UserInfoRespVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class PayController {

    @Reference(interfaceClass = PayService.class)
    PayService payService;


    @RequestMapping("getPayInfo")
    public ResponseEntity<?> getPayInfo(@Param("orderId") String orderId){
        boolean flag = false;
        try {
            flag = payService.getPayInfo(orderId);
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.SYSTEM_ERROR);
        }
//        boolean flag = payService.getPayInfo(orderId);
        UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
        if (flag){
            PayInfo payInfo = new PayInfo();
            payInfo.setOrderId(orderId);
            payInfo.setQRCodeAddress("qr-"+orderId+".png");
            userInfoRespVo.setStatus(0);
            userInfoRespVo.setImgPre("/Users/user/Desktop/Project_guns/");
            userInfoRespVo.setData(payInfo);
        }else {
            userInfoRespVo.setStatus(1);
            userInfoRespVo.setMsg("订单支付失败，请稍后重试");
        }
        return ResponseEntity.ok(userInfoRespVo);
    }

    @RequestMapping("getPayResult")
    public ResponseEntity getPayResult(@Param("orderId") String orderId,@Param("tryNums") Integer tryNums){
        UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
        if (tryNums>5){
            userInfoRespVo.setMsg("订单支付失败，请稍后重试");
            userInfoRespVo.setStatus(1);
            return ResponseEntity.ok(userInfoRespVo);
        }
        Integer status = payService.checkOrderStatusAndChange(orderId);
        if(status==1){
            PayRespVo payRespVo = new PayRespVo();
            payRespVo.setOrderId(orderId);
            payRespVo.setOrderMsg("支付成功");
            payRespVo.setOrderStatus(1);
            userInfoRespVo.setData(payRespVo);
            userInfoRespVo.setStatus(0);
            return ResponseEntity.ok(userInfoRespVo);
        }
        userInfoRespVo.setMsg("订单支付失败，请稍后重试");
        userInfoRespVo.setStatus(1);
        return ResponseEntity.ok(userInfoRespVo);
    }
}
