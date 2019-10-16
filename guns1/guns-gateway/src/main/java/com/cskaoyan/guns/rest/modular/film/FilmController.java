package com.cskaoyan.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.rest.service.FilmService;
import com.cskaoyan.guns.rest.vo.filmVo.FilmDetailRequestVo;
import com.cskaoyan.guns.rest.vo.filmVo.FilmResponseVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Reference(interfaceClass = FilmService.class)
    private FilmService filmService;

    @GetMapping("getFilms")
    public FilmResponseVo getFilms(FilmDetailRequestVo requestVo){
        if (requestVo.getNowPage()==null){
            requestVo.setNowPage(1);
        }
        FilmResponseVo responseVo = filmService.getFilms(requestVo);
        return responseVo;
    }
    @GetMapping("/films/{id}")
    public FilmResponseVo filmDetail(@PathVariable Integer id){
        FilmResponseVo responseVo = filmService.filmDetail(id);
        return responseVo;
    }
}
