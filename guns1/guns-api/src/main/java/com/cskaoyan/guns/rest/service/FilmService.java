package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.vo.filmVo.*;

import java.util.List;

public interface FilmService {
    FilmResponseVo getFilms(FilmDetailRequestVo requestVo);

    FilmResponseVo filmDetail(Integer id);

    List<BannerVo> getIndexBanners();

    FilmInfoDataVo getIndexHotFilms();

    FilmInfoDataVo getIndexSoonFilms();

    List<FilmInfoVo> getIndexBoxRanking();

    List<FilmInfoVo> getIndexExpectRanking();

    List<FilmInfoVo> getIndexTop100();

    List<CatInfoVo> getCatInfo();

    List<SourceInfoVo> getSourceInfo();

    List<YearInfoVo> getYearInfo();
}
