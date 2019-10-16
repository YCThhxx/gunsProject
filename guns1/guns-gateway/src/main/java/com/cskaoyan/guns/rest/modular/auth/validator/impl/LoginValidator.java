package com.cskaoyan.guns.rest.modular.auth.validator.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.core.util.MD5Util;
import com.cskaoyan.guns.rest.modular.auth.validator.IReqValidator;
import com.cskaoyan.guns.rest.modular.auth.validator.dto.Credence;
import com.cskaoyan.guns.rest.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Tyj
 * @date 2019/10/15 20:49
 */
@Service
public class LoginValidator implements IReqValidator {
    @Reference(interfaceClass = UserService.class)
    UserService userService;

    @Override
    public boolean validate(Credence credence) {
        String password = credence.getCredenceCode();
        String userName = credence.getCredenceName();
        String salt = "wwk999";
        String encryptpwd = MD5Util.encrypt(password + salt);

        String pwd = null;
        int count = userService.check(userName);
        if(count==1){
            pwd = userService.checkPassword(userName);
        }else {
            return false;
        }
        if (pwd.equals(encryptpwd)) {
            return true;
        } else {
            return false;
        }

    }
}
