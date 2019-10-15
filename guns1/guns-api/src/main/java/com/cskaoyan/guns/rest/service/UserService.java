package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.respVo.userInfo.UserInfo;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfoRespVo;

public interface UserService {


    UserInfoRespVo getUserInfoByUUID(int uuid);

    boolean updateUserInfo(UserInfo userInfo);
}
