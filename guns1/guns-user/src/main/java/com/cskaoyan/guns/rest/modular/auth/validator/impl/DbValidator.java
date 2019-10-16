package com.cskaoyan.guns.rest.modular.auth.validator.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
<<<<<<< HEAD
/*import com.cskaoyan.guns.rest.common.persistence.dao.UserMapper;
import com.cskaoyan.guns.rest.common.persistence.model.User;*/
=======
>>>>>>> 3f26613456860ca4b6cd31a436c04a1de94b811f
import com.cskaoyan.guns.rest.modular.auth.validator.IReqValidator;
import com.cskaoyan.guns.rest.modular.auth.validator.dto.Credence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
/*
@Service
public class DbValidator implements IReqValidator {
<<<<<<< HEAD
*/
/*
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean validate(Credence credence) {
        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("userName", credence.getCredenceName()));
        if (users != null && users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }*//*

=======

//    @Autowired
//    UserMapper userMapper;

    @Override
    public boolean validate(Credence credence) {
//        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("userName", credence.getCredenceName()));
//        if (users != null && users.size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
>>>>>>> 3f26613456860ca4b6cd31a436c04a1de94b811f
}
*/
