/*package com.cskaoyan.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;

import com.cskaoyan.guns.rest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference(interfaceClass = UserService.class)
     UserService userService;

    @RequestMapping("getUserNameById")
    public String getusername(Integer id){
        System.out.println("haha");
        String usernameById = userService.getUsernameById(id);
        return usernameById;
    }

}*/
