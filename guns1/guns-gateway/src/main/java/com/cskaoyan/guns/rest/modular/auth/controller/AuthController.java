package com.cskaoyan.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.core.exception.GunsException;
import com.cskaoyan.guns.rest.common.exception.BizExceptionEnum;
import com.cskaoyan.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.cskaoyan.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.cskaoyan.guns.rest.modular.auth.util.JwtTokenUtil;
import com.cskaoyan.guns.rest.modular.auth.validator.IReqValidator;

import com.cskaoyan.guns.rest.service.UserService;
import com.cskaoyan.guns.rest.vo.respVo.userInfo.UserInfoRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private Jedis jedis;
    @Reference(interfaceClass = UserService.class)
    UserService userService;

    @Resource(name = "loginValidator")
    IReqValidator reqValidator;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {
        String credenceName = authRequest.getUserName();
        boolean validate = reqValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            String userId = userService.login(credenceName);
            jedis.set(token,userId);
            jedis.expire(token,36000);
            UserInfoRespVo userInfoRespVo = new UserInfoRespVo();
            userInfoRespVo.setData(new AuthResponse(token,randomKey));
            userInfoRespVo.setStatus(0);
            return ResponseEntity.ok(userInfoRespVo);
        } else {
            throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
