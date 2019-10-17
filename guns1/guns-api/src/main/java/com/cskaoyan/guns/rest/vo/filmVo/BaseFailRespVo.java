package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;

public class BaseFailRespVo implements Serializable {

    private static final long serialVersionUID = -5169680870359249533L;
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static BaseFailRespVo fail(int status,String msg){
        BaseFailRespVo baseFailRespVo = new BaseFailRespVo();
        baseFailRespVo.setStatus(status);
        baseFailRespVo.setMsg(msg);
        return baseFailRespVo;
    }
}
