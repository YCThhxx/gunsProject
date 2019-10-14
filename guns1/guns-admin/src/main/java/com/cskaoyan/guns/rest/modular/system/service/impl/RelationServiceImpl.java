package com.cskaoyan.guns.rest.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cskaoyan.guns.rest.modular.system.dao.RelationMapper;
import com.cskaoyan.guns.rest.modular.system.model.Relation;
import com.cskaoyan.guns.rest.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}
