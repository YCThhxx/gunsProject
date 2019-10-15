package com.cskaoyan.guns.cinema.common.persistence.dao;

import com.cskaoyan.guns.cinema.common.persistence.model.MtimeBannerT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * banner信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-10-14
 */
public interface MtimeBannerTMapper extends BaseMapper<MtimeBannerT> {

    MtimeBannerT getbannerInfo(@Param("id") Integer brandId);
}
