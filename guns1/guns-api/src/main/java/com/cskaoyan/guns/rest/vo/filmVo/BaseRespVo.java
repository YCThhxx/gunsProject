package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;

public class BaseRespVo<T> implements Serializable {

    private static final long serialVersionUID = 5840575091369144148L;
    Object data;
    String imgPre;
    String msg;
    String nowPage;
    int status;
    String totalPage;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNowPage() {
        return nowPage;
    }

    public void setNowPage(String nowPage) {
        this.nowPage = nowPage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "BaseRespVo{" +
                "data=" + data +
                ", imgPre='" + imgPre + '\'' +
                ", msg='" + msg + '\'' +
                ", nowPage='" + nowPage + '\'' +
                ", status=" + status +
                ", totalPage='" + totalPage + '\'' +
                '}';
    }

    public static BaseRespVo ok(Object data, String imgPre, String msg, String nowPage, int status, String totalPage){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        baseRespVo.setImgPre(imgPre);
        baseRespVo.setNowPage(nowPage);
        baseRespVo.setStatus(status);
        baseRespVo.setTotalPage(totalPage);
        return baseRespVo;
    }
}
