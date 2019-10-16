package com.cskaoyan.guns.rest.modular.cinema;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;
import com.cskaoyan.guns.rest.vo.cinemaVo.CinemaVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaService.class)
    private  CinemaService cinemaService;

    @RequestMapping("getCinemas")
    public CinemaVo getCinemas(Integer brandId, Integer  hallType, Integer areaId, Integer pageSize, Integer nowPage){
        CinemaVo cinemas = cinemaService.getCinemas(brandId, hallType, areaId, pageSize, nowPage);
        return  cinemas;
    }
    @RequestMapping("getCondition")
    public  CinemaVo getCondition(Integer brandId,Integer  hallType,Integer areaId){
        CinemaVo cinemas = cinemaService.getCondition(brandId, hallType, areaId);
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
