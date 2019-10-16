package com.cskaoyan.guns.rest.modular.auth.validator.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cskaoyan.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.cskaoyan.guns.rest.common.persistence.model.MtimeUserT;
import com.cskaoyan.guns.rest.modular.auth.validator.IReqValidator;
import com.cskaoyan.guns.rest.modular.auth.validator.dto.Credence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 直接验证账号密码是不是admin
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class SimpleValidator implements IReqValidator {

    @Autowired
    MtimeUserTMapper mapper;
    private static String USER_NAME = "admin";

    private static String PASSWORD = "admin";


    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        if (USER_NAME.equals(userName) && PASSWORD.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
