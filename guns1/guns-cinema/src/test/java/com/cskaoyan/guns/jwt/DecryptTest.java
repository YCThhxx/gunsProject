package com.cskaoyan.guns.jwt;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.guns.core.util.MD5Util;
import com.cskaoyan.guns.cinema.common.SimpleObject;
import com.cskaoyan.guns.cinema.modular.auth.converter.BaseTransferEntity;
import com.cskaoyan.guns.cinema.modular.auth.security.impl.Base64SecurityAction;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String salt = "0iqwhi";

        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("cskaoyan");
        simpleObject.setAge(12);
        simpleObject.setName("ffff");
        simpleObject.setTips("code");

        String jsonString = JSON.toJSONString(simpleObject);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
