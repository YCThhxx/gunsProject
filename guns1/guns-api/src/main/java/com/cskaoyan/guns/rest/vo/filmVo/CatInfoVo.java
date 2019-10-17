package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatInfoVo implements Serializable {

    private static final long serialVersionUID = 4843287587142366121L;
    private String catId;
    private String catName;
    private boolean isActive;

}
