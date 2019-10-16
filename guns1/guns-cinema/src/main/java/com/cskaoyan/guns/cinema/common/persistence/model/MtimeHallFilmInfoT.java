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


}
