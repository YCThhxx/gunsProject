package com.cskaoyan.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cskaoyan.guns.rest.service.FilmService;
import com.cskaoyan.guns.rest.vo.filmVo.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getIndex")
    public Object getIndex(){
        List<BannerVo> banners;
        banners = filmService.getIndexBanners();
        FilmInfoDataVo hotFilms = filmService.getIndexHotFilms();
        FilmInfoDataVo soonFilms = filmService.getIndexSoonFilms();
        List<FilmInfoVo> boxRanking = filmService.getIndexBoxRanking();
        List<FilmInfoVo> expectRanking = filmService.getIndexExpectRanking();
        List<FilmInfoVo> top100 = filmService.getIndexTop100();
        if(banners==null||banners.size()==0){
            return BaseFailRespVo.fail(1,"查询失败，无banner可加载");
        }
        IndexDataVo indexDataVo = new IndexDataVo(banners,hotFilms,soonFilms,boxRanking,expectRanking,top100);
        return BaseRespVo.ok(indexDataVo,"http://img.meetingshop.cn/","","",0,"");
    }

    @GetMapping("getConditionList")
    public Object getConditionList(){
        List<CatInfoVo> catInfo = filmService.getCatInfo();
        List<SourceInfoVo> sourceInfo = filmService.getSourceInfo();
        List<YearInfoVo> yearInfo = filmService.getYearInfo();
        if(catInfo==null&&sourceInfo==null&&yearInfo==null){
            return BaseFailRespVo.fail(1,"查询失败，无条件可加载");
        }
        ConditionListVo conditionListVo = new ConditionListVo(catInfo,sourceInfo,yearInfo);
        return BaseRespVo.ok(conditionListVo,"","","",0,"");
    }
}
