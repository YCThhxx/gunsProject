package com.cskaoyan.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cskaoyan.guns.rest.UserService;
import com.cskaoyan.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.cskaoyan.guns.rest.common.persistence.model.MtimeUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private MtimeUserTMapper mtimeUserTMapper;

    @Override
    public String getUsernameById(int id) {
        MtimeUserT user = mtimeUserTMapper.selectById(id);
        String userName = user.getUserName();
//        System.out.println(userName);
        return userName;
    }
}
