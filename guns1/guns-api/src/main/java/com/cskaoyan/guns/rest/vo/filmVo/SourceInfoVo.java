package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SourceInfoVo implements Serializable {

    private static final long serialVersionUID = 1855672719985175379L;
    private String sourceId;
    private String sourceName;
    private boolean isActive;
}
