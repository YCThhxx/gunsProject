package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseVo<T> implements Serializable {
    T data;

    String imgPre = "http://img.meetingshop.cn/";

    String msg;

    String nowPage;

    Integer status;

    String  totalPage;

}
