package com.cskaoyan.guns.rest.vo.cinemaVo;

import java.io.Serializable;

public class CinemaVo<T> implements Serializable  {
    private Integer status;
    private  Integer nowPage;
    private  Integer totalPage;
    private  String msg;
    T data;

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CinemaVo{" +
                "status=" + status +
                ", noePage=" + nowPage +
                ", totalPage=" + totalPage +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

   
    public  static CinemaVo ok(Object data){
        CinemaVo<Object> objectCinemaVo = new CinemaVo<>();
        objectCinemaVo.setData(data);
        objectCinemaVo.setStatus(0);
        return  objectCinemaVo;
    }
    public static CinemaVo businessError(int status){
        CinemaVo<Object> objectCinemaVo = new CinemaVo<>();
        objectCinemaVo.setMsg("影院信息查询失败");
        objectCinemaVo.setStatus(status);
        return  objectCinemaVo;
    }
    public static CinemaVo systemError(int status){
        CinemaVo<Object> objectCinemaVo = new CinemaVo<>();
        objectCinemaVo.setMsg("系统出现异常，请联系管理员");
        objectCinemaVo.setStatus(status);
        return  objectCinemaVo;
    }
}
