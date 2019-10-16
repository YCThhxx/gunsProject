package com.cskaoyan.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.cskaoyan.guns.core.util.MD5Util;
import com.cskaoyan.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.cskaoyan.guns.rest.requestVo.Register;

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


    //检查用户名是否存在
    @Override
    public int check(String username) {
        Wrapper<MtimeUserT> wrapper = new EntityWrapper<MtimeUserT>();
        wrapper.eq("user_name",username);
        Integer count = mtimeUserTMapper.selectCount(wrapper);
        return count;
    }

    //注册用户
    @Override
    public int register(Register register) {
        String username = register.getUsername();
        String password = register.getPassword();
        String address = register.getAddress();
        String email = register.getEmail();
        String mobile = register.getMobile();
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUserName(username);
        mtimeUserT.setAddress(address);
        mtimeUserT.setEmail(email);
        mtimeUserT.setUserPhone(mobile);
        //暂时先把盐值写死
        String salt = "wwk999";
        String md5 = MD5Util.encrypt(password + salt);
        mtimeUserT.setUserPwd(md5);
        Date date = new Date();
        mtimeUserT.setBeginTime(date);
        mtimeUserT.setUpdateTime(date);
        Integer insert = mtimeUserTMapper.insert(mtimeUserT);
        return insert;
    }


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

    //获取用户的ID
    @Override
    public String login(String username) {
        String id = mtimeUserTMapper.queryIdByUserName(username);
        return id;
    }

    //检查用户密码
    @Override
    public String checkPassword(String userName) {
        String password = mtimeUserTMapper.checkPassword(userName);
        return password;
    }

}
