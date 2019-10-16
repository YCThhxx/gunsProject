package com.cskaoyan.guns.rest.vo.cinemaVo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class FieldsData implements Serializable {
    CinemaInfo cinemaInfo;

    Set<FilmList> filmList;
}
