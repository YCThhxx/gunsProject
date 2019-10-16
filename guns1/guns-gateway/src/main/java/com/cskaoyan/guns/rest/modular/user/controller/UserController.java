package com.cskaoyan.guns.rest.modular.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.core.exception.GunsException;
import com.cskaoyan.guns.rest.common.exception.BizExceptionEnum;
import com.cskaoyan.guns.rest.requestVo.Register;
import com.cskaoyan.guns.rest.respVo.UserResp;
import com.cskaoyan.guns.rest.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @RequestMapping("check")
    public UserResp check(@RequestBody HashMap map){
        String username = (String) map.get("username");
        int check = userService.check(username);
        if(check==1){
            return  UserResp.resp("用户名已经注册",1);
        }else{
            return UserResp.resp("用户名不存在",0);
        }
    }
    @RequestMapping("register")
    public UserResp login(@RequestBody Register register){
        int i = userService.register(register);
        if(i==1){
            return UserResp.resp("用户注册成功!",0);
        }
        throw new GunsException(BizExceptionEnum.REGISTER_ERROR);
    }

}
