package com.cskaoyan.guns.rest.service;



import com.cskaoyan.guns.rest.requestVo.Register;

import com.cskaoyan.guns.rest.respVo.userInfo.UserInfo;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfoRespVo;


public interface UserService {



    //检查用户名是否存在
    int check(String username);
    //注册用户
    int register(Register register);
    //获取用户的ID
    String login(String username);
    //检查用户密码
    String checkPassword(String userName);
    UserInfoRespVo getUserInfoByUUID(int uuid);
    boolean updateUserInfo(UserInfo userInfo);
    Integer getUuidByUserName(String username);
}
