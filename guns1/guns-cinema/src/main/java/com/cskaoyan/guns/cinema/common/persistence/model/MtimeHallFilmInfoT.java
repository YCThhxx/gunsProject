package com.cskaoyan.guns.cinema.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 影厅电影信息表
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */
@Data
public class MtimeHallFilmInfoT implements Serializable {



    /**
     * 主键编号
     */
    private Integer uuid;
    /**
     * 电影编号
     */

    private Integer filmId;

    private String filmName;
    /**
     * 电影时长
     */

    private String filmLength;
    /**
     * 电影类型
     */

    private String filmCats;
    /**
     * 电影语言
     */

    private String filmLanguage;
    /**
     * 演员列表
     */
    private String actors;

    List<MtimeFieldT> filmFields;
    /**
     * 图片地址
     */

    private String imgAddress;

    private String filmType;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmCats() {
        return filmCats;
    }

    public void setFilmCats(String filmCats) {
        this.filmCats = filmCats;
    }

    public String getFilmLanguage() {
        return filmLanguage;
    }

    public void setFilmLanguage(String filmLanguage) {
        this.filmLanguage = filmLanguage;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public List<MtimeFieldT> getFilmFields() {
        return filmFields;
    }

    public void setFilmFields(List<MtimeFieldT> filmFields) {
        this.filmFields = filmFields;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }
}
