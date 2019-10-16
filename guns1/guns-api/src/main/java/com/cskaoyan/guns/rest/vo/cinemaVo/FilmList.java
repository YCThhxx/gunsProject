package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmList implements Serializable {
    String actors;

    String filmCats;

    List<FilmFields> filmFields;

    Integer filmId;

    String filmLength;

    String filmName;

    String filmType;

    String imgAddress;

}
