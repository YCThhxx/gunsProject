package com.cskaoyan.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfo;
import com.cskaoyan.guns.rest.respVo.userInfo.UserInfoRespVo;
import com.cskaoyan.guns.rest.service.UserService;
import com.cskaoyan.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.cskaoyan.guns.rest.common.persistence.model.MtimeUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private MtimeUserTMapper mtimeUserTMapper;


    @Override
    public UserInfoRespVo getUserInfoByUUID(int uuid) {
        UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
        List<MtimeUserT> user = mtimeUserTMapper.selectList(new EntityWrapper<MtimeUserT>().eq("UUID", uuid));
        if (user.size()==1){
            MtimeUserT mtimeUserT = user.get(0);
            UserInfo userInfo = new UserInfo();
            userInfo.setUuid(mtimeUserT.getUuid());
            userInfo.setUsername(mtimeUserT.getUserName());
            userInfo.setNickname(mtimeUserT.getNickName());
            userInfo.setEmail(mtimeUserT.getEmail());
            userInfo.setPhone(mtimeUserT.getUserPhone());
            userInfo.setSex(mtimeUserT.getUserSex());
            userInfo.setBirthday(mtimeUserT.getBirthday());
            userInfo.setLifeState(mtimeUserT.getLifeState());
            userInfo.setBiography(mtimeUserT.getBiography());
            userInfo.setAddress(mtimeUserT.getAddress());
            userInfo.setHeadAddress(mtimeUserT.getHeadUrl());
            userInfo.setCreateTime(mtimeUserT.getBeginTime());
            userInfo.setUpdateTime(new Date());
            userInfoRespVo.setStatus(0);
            userInfoRespVo.setData(userInfo);
            userInfoRespVo.setImgPre(mtimeUserT.getHeadUrl());
            return userInfoRespVo;
        }else {
            userInfoRespVo.setData("用户信息查询出错");
            userInfoRespVo.setStatus(220);
            return userInfoRespVo;
        }

    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUuid(userInfo.getUuid());
        mtimeUserT.setUserName(userInfo.getUsername());
        mtimeUserT.setNickName(userInfo.getNickname());
        mtimeUserT.setEmail(userInfo.getEmail());
        mtimeUserT.setUserPhone(userInfo.getPhone());
        mtimeUserT.setUserSex(userInfo.getSex());
        mtimeUserT.setBirthday(userInfo.getBirthday());
        mtimeUserT.setLifeState(userInfo.getLifeState());
        mtimeUserT.setBiography(userInfo.getBiography());
        mtimeUserT.setAddress(userInfo.getAddress());
        mtimeUserT.setHeadUrl(userInfo.getHeadAddress());
        mtimeUserT.setUpdateTime(new Date());
        Integer integer = mtimeUserTMapper.updateAllColumnById(mtimeUserT);
        if (integer==1){
            return true;
        }else {
            return false;
        }
    }
}
