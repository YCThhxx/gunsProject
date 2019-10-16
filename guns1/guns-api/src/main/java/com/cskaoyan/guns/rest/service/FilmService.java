package com.cskaoyan.guns.rest.service;

import com.cskaoyan.guns.rest.vo.filmVo.FilmDetailRequestVo;
import com.cskaoyan.guns.rest.vo.filmVo.FilmResponseVo;

public interface FilmService {
    FilmResponseVo getFilms(FilmDetailRequestVo requestVo);

    FilmResponseVo filmDetail(Integer id);
}
