<<<<<<< HEAD:guns1/guns-rest/src/main/java/com/cskaoyan/guns/rest/modular/auth/controller/dto/AuthResponse.java
package com.cskaoyan.guns.rest.modular.auth.controller.dto;
=======
package com.cskaoyan.guns.cinema.modular.auth.controller.dto;
>>>>>>> 3f26613456860ca4b6cd31a436c04a1de94b811f:guns1/guns-cinema/src/main/java/com/cskaoyan/guns/cinema/modular/auth/controller/dto/AuthResponse.java

import java.io.Serializable;

/**
 * 认证的响应结果
 *
 * @author fengshuonan
 * @Date 2017/8/24 13:58
 */
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    /**
     * jwt token
     */
    private final String token;

    /**
     * 用于客户端混淆md5加密
     */
    private final String randomKey;

    public AuthResponse(String token, String randomKey) {
        this.token = token;
        this.randomKey = randomKey;
    }

    public String getToken() {
        return this.token;
    }

    public String getRandomKey() {
        return randomKey;
    }
}
