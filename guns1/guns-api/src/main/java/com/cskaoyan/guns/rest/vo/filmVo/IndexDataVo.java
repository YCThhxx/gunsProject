package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;
import java.util.List;

public class IndexDataVo implements Serializable {

    private static final long serialVersionUID = -17371661169587736L;
    List<BannerVo> banners;
    FilmInfoDataVo hotFilms;
    FilmInfoDataVo soonFilms;
    List<FilmInfoVo> boxRanking;
    List<FilmInfoVo> expectRanking;
    List<FilmInfoVo> top100;

    public IndexDataVo(List<BannerVo> banners, FilmInfoDataVo hotFilms, FilmInfoDataVo soonFilms, List<FilmInfoVo> boxRanking, List<FilmInfoVo> expectRanking, List<FilmInfoVo> top100) {
        this.banners = banners;
        this.hotFilms = hotFilms;
        this.soonFilms = soonFilms;
        this.boxRanking = boxRanking;
        this.expectRanking = expectRanking;
        this.top100 = top100;
    }

    public List<BannerVo> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerVo> banners) {
        this.banners = banners;
    }

    public FilmInfoDataVo getHotFilms() {
        return hotFilms;
    }

    public void setHotFilms(FilmInfoDataVo hotFilms) {
        this.hotFilms = hotFilms;
    }

    public FilmInfoDataVo getSoonFilms() {
        return soonFilms;
    }

    public void setSoonFilms(FilmInfoDataVo soonFilms) {
        this.soonFilms = soonFilms;
    }

    public List<FilmInfoVo> getBoxRanking() {
        return boxRanking;
    }

    public void setBoxRanking(List<FilmInfoVo> boxRanking) {
        this.boxRanking = boxRanking;
    }

    public List<FilmInfoVo> getExpectRanking() {
        return expectRanking;
    }

    public void setExpectRanking(List<FilmInfoVo> expectRanking) {
        this.expectRanking = expectRanking;
    }

    public List<FilmInfoVo> getTop100() {
        return top100;
    }

    public void setTop100(List<FilmInfoVo> top100) {
        this.top100 = top100;
    }
}
