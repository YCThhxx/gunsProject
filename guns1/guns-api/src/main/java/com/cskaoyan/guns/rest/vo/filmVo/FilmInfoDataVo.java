package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;
import java.util.List;

public class FilmInfoDataVo implements Serializable {

    private static final long serialVersionUID = -5082517288973626190L;
    int filmNum;
    List<FilmInfoVo> filmInfo;

    public int getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(int filmNum) {
        this.filmNum = filmNum;
    }

    public List<FilmInfoVo> getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(List<FilmInfoVo> filmInfo) {
        this.filmInfo = filmInfo;
    }
}
