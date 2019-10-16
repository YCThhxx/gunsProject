package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;

public class Director implements Serializable {
    private static final long serialVersionUID = -7209623425854347709L;
    private String imgAddress;
    private String directorName;

    public Director(String actorImg, String actorName) {
        this.imgAddress = actorImg;
        this.directorName = actorName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
