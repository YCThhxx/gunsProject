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
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfo;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfoRespVo;
import com.cskaoyan.guns.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;



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

    @Autowired
    Jedis jedis;

    @RequestMapping("getUserInfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String uuid1 = jedis.get(token);
        int uuid = Integer.parseInt(uuid1);
//            Integer uuid = 2;
        UserInfoRespVo userInfoRespVo = null;
        try {
            userInfoRespVo = userService.getUserInfoByUUID(uuid);
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.SYSTEM_ERROR);
        }
        return ResponseEntity.ok(userInfoRespVo);
    }

    @RequestMapping("updateUserInfo")
    public ResponseEntity<?> updateUserInfo(UserInfo userInfo){
        Integer uuid = userInfo.getUuid();
        boolean flag = userService.updateUserInfo(userInfo);
        if (flag){
            UserInfoRespVo userInfoRespVo = null;
            try {
                userInfoRespVo = userService.getUserInfoByUUID(uuid);
            } catch (Exception e) {
                throw new GunsException(BizExceptionEnum.SYSTEM_ERROR);
            }
            return ResponseEntity.ok(userInfoRespVo);
        }else {
            UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
            userInfoRespVo.setStatus(1);
            userInfoRespVo.setMsg("用户信息修改失败");
            return ResponseEntity.ok(userInfoRespVo);
        }
    }

    @RequestMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
        //删除token
        Long del = jedis.del(token);
        if (del == 1) {
            userInfoRespVo.setMsg("退出成功");
            userInfoRespVo.setStatus(0);
            return ResponseEntity.ok(userInfoRespVo);
        }else {
            userInfoRespVo.setMsg("退出失败，用户尚未登陆");
            userInfoRespVo.setStatus(1);
            return ResponseEntity.ok(userInfoRespVo);
        }

    }

}
