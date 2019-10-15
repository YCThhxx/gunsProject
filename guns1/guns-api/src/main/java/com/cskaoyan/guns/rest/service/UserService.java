package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.respVo.userInfo.UserInfo;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfoRespVo;

import java.util.HashMap;

public interface UserService {


    UserInfoRespVo getUserInfoByUUID(Integer uuid);

    boolean updateUserInfo(UserInfo userInfo);
}
