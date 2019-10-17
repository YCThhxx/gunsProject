package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerVo implements Serializable {

    private static final long serialVersionUID = -6305350414252951037L;
    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;

}
