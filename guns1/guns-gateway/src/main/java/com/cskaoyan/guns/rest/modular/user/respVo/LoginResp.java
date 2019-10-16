package com.cskaoyan.guns.rest.modular.user.respVo;

/**
 * @author Tyj
 * @date 2019/10/15 15:25
 */
public class LoginResp<T> {
    T data;
    String imgPre;
    String msg;
    String nowPage;
    Integer status;
    Integer totalPage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public LoginResp ok(){
        LoginResp loginResp = new LoginResp();
        loginResp.setMsg("用户注册成功");
        loginResp.setStatus(0);
        return loginResp;
    }

    @Override
    public String toString() {
        return "UserResp{" +
                "data=" + data +
                ", imgPre='" + imgPre + '\'' +
                ", msg='" + msg + '\'' +
                ", nowPage='" + nowPage + '\'' +
                ", status=" + status +
                ", totalPage=" + totalPage +
                '}';
    }
}
