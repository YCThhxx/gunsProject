package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 6448882366999397292L;
    Integer status;
    String imgPre;
    Integer nowPage;
    Integer totalPage;
    String msg;
    T data;

}
