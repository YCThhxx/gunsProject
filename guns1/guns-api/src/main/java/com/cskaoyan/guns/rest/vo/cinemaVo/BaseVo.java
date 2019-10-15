package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseVo<T> implements Serializable {
    T data;

    String imgPre;

    String msg;

    String nowPage;

    Integer status;

    String  totalPage;

}
