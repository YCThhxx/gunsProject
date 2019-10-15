package com.cskaoyan.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.vo.cinemaVo.BaseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaService.class)
    CinemaService cinemaService;

    @RequestMapping("getFields")
    public BaseVo getFields(@RequestParam Integer cinemaId){
       BaseVo baseVo = cinemaService.getFields(cinemaId);
        return baseVo;
    }

    @RequestMapping("getFieldInfo")
    public BaseVo getFieldInfo(@RequestParam Integer cinemaId,
                              @RequestParam Integer fieldId){
        BaseVo baseVo = cinemaService.getFieldInfo(cinemaId,fieldId);
        System.out.println("hah");
        return baseVo;
    }

}
