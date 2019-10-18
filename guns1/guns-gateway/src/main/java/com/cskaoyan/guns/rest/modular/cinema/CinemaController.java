package com.cskaoyan.guns.rest.modular.cinema;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.core.exception.GunsException;
import com.cskaoyan.guns.rest.common.exception.BizExceptionEnum;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;
import com.cskaoyan.guns.rest.vo.cinemaVo.CinemaVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "电影模块请求")
@RequestMapping("/cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaService.class)
    private  CinemaService cinemaService;

    @RequestMapping("getCinemas")
    public CinemaVo getCinemas(Integer brandId, Integer  hallType, Integer areaId, Integer pageSize, Integer nowPage,Integer halltypeId){
        int hallId = 0;
        if (hallType != null){
            hallId = hallType;
        }
        if (halltypeId != null){
            hallId = halltypeId;
        }
        CinemaVo cinemas;
        try {
            cinemas = cinemaService.getCinemas(brandId, hallId, areaId, pageSize, nowPage);
        }catch (Exception e){
            throw new GunsException(BizExceptionEnum.BUSSNISS_ERROR);
        }
        return  cinemas;
    }
    @RequestMapping("getCondition")
    public  CinemaVo getCondition(Integer brandId,Integer  hallType,Integer areaId){
        CinemaVo cinemas;
        try {
            cinemas = cinemaService.getCondition(brandId, hallType, areaId);
        }catch (Exception e){
            throw new GunsException(BizExceptionEnum.BUSSNISS_ERROR);
        }
        return  cinemas;
    }

    @RequestMapping("getFields")
    public BaseVo getFields(@RequestParam Integer cinemaId){
       BaseVo baseVo = cinemaService.getFields(cinemaId);
        return baseVo;
    }

    @RequestMapping("getFieldInfo")
    public BaseVo getFieldInfo(@RequestParam Integer cinemaId,
                              @RequestParam Integer fieldId){
        BaseVo baseVo = cinemaService.getFieldInfo(cinemaId,fieldId);
        return baseVo;
    }
    
}
