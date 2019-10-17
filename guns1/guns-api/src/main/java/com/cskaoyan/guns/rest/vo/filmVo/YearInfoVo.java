package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class YearInfoVo implements Serializable {

    private static final long serialVersionUID = 2134242514738294423L;
    private String yearId;
    private String yearName;
    private boolean isActive;

}
