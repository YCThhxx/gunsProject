package com.cskaoyan.guns.cinema.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 影院信息表
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */

@Data
public class MtimeCinemaT  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    private Integer cinemaId;
    /**
     * 影院名称
     */
    @TableField("cinema_name")
    private String cinemaName;
    /**
     * 影院电话
     */
    @TableField("cinema_phone")
    private String cinemaPhone;
    /**
     * 品牌编号
     */
    @TableField("brand_id")
    private Integer brandId;
    /**
     * 地域编号
     */
    @TableField("area_id")
    private Integer areaId;
    /**
     * 包含的影厅类型,以#作为分割
     */
    @TableField("hall_ids")
    private String hallIds;
    /**
     * 影院图片地址
     */
    @TableField("img_address")
    private String imgAddress;
    /**
     * 影院地址
     */
    @TableField("cinema_address")
    private String cinemaAddress;
    /**
     * 最低票价
     */
    @TableField("minimum_price")
    private Integer minimumPrice;


}
