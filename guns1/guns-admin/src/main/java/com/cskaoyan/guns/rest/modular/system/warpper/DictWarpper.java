package com.cskaoyan.guns.rest.modular.system.warpper;

import com.cskaoyan.guns.core.common.constant.factory.ConstantFactory;
import com.cskaoyan.guns.rest.modular.system.model.Dict;
import com.cskaoyan.guns.core.base.warpper.BaseControllerWarpper;
import com.cskaoyan.guns.core.util.ToolUtil;

import java.util.List;
import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DictWarpper extends BaseControllerWarpper {

    public DictWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Integer id = Integer.valueOf(map.get("id").toString());
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if(dicts != null){
            for (Dict dict : dicts) {
                detail.append(dict.getCode() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
    }

}
