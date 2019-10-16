package com.cskaoyan.guns.film.modular.film.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cskaoyan.guns.film.common.persistence.dao.*;
import com.cskaoyan.guns.film.common.persistence.model.*;
import com.cskaoyan.guns.rest.service.FilmService;
import com.cskaoyan.guns.rest.vo.filmVo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class FilmServiceImpl implements FilmService {

    @Autowired
    private MtimeFilmTMapper mtimeFilmTMapper;

    @Autowired
    private MtimeFilmInfoTMapper mtimeFilmInfoTMapper;

    @Autowired
    private MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Autowired
    private MtimeSourceDictTMapper mtimesourceDictTMapper;

    @Autowired
    private MtimeActorTMapper mtimeActorTMapper;

    @Autowired
    private MtimeFilmActorTMapper mtimeFilmActorTMapper;

    @Override
    public FilmResponseVo getFilms(FilmDetailRequestVo requestVo) {
        FilmResponseVo responseVo = new FilmResponseVo();
        Page<MtimeFilmT> page = new Page<>();
        page.setSize(requestVo.getPageSize());
        page.setCurrent(requestVo.getNowPage());

        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", requestVo.getShowType());
        if (requestVo.getYearId()!=99){
            entityWrapper.eq("film_source", requestVo.getSourceId());
        }
        if (requestVo.getSourceId()!=99){
            entityWrapper.eq("film_date", requestVo.getYearId());
        }
        if (requestVo.getCatId()!=99) {
            entityWrapper.like("film_cats", requestVo.getCatId()+"");
        }
        List<MtimeFilmT> filmTList = mtimeFilmTMapper.selectPage(page, entityWrapper);
        List<FilmVo> filmDetailVoList = convert2FilmDetailVo(filmTList);
        responseVo.setData(filmDetailVoList);
        responseVo.setImgPre("http://img.meetingshop.cn/");
        responseVo.setStatus(0);
        responseVo.setNowPage(requestVo.getNowPage());
        long total = page.getTotal();
        responseVo.setTotalPage((int)total/requestVo.getPageSize()+1);
        return responseVo;
    }

    @Override
    public FilmResponseVo filmDetail(Integer id) {
        FilmResponseVo responseVo = new FilmResponseVo();
        MtimeFilmT filmT = getFilmById(id);
        MtimeFilmInfoT filmInfoT = getFilmInfoByFilmId(id);
        MtimeHallFilmInfoT hallFilmInfoT = getHallFilmInfo(id);
        String filmArea = getFilmArea(filmT.getFilmArea());
        ActorsVo actorsVo = getActorsVoByFilmId(id, filmInfoT);

        responseVo.setImgPre("http://img.meetingshop.cn/");
        responseVo.setStatus(0);
        FilmDetailVo filmDetailVo = new FilmDetailVo();
        filmDetailVo.setFilmName(filmT.getFilmName());
        filmDetailVo.setFilmEnName(filmInfoT.getFilmEnName());
        filmDetailVo.setImgAddress(filmT.getImgAddress());
        filmDetailVo.setScore(filmT.getFilmScore());
        filmDetailVo.setFilmId(id+"");
        filmDetailVo.setScoreNum(filmInfoT.getFilmScoreNum()+"万人评分");
        filmDetailVo.setTotalBox(filmT.getFilmBoxOffice()+"万");
        filmDetailVo.setInfo01(hallFilmInfoT.getFilmCats());
        filmDetailVo.setInfo02(filmArea+ "/" +hallFilmInfoT.getFilmLength()+"分钟");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(filmT.getFilmTime());
        filmDetailVo.setInfo03(date + filmArea+"上映");

        Info04Vo info04 = new Info04Vo();
        info04.setBiography(filmInfoT.getBiography());
        info04.setActors(actorsVo);
        ImgVo imgVo = new ImgVo();
        String img = filmInfoT.getFilmImgs();
        String[] imgArray = img.split(",");
        imgVo.setMainImg(imgArray[0]);
        imgVo.setImg01(imgArray[1]);
        imgVo.setImg02(imgArray[2]);
        imgVo.setImg03(imgArray[3]);
        imgVo.setImg04(imgArray[4]);
        filmDetailVo.setImgVO(imgVo);
        filmDetailVo.setInfo04(info04);

        responseVo.setData(filmDetailVo);
        return responseVo;
    }

    private ActorsVo getActorsVoByFilmId(Integer id, MtimeFilmInfoT filmInfoT) {
        ActorsVo actorsVo = new ActorsVo();
        Director director = getDirector(filmInfoT.getDirectorId());
        actorsVo.setDirector(director);
        List<MtimeFilmActorT> actorTList = getActorTListByFilmId(id);
        List<Actor> actorList = getActorList(actorTList);
        actorsVo.setActors(actorList);
        return actorsVo;
    }

    private List<Actor> getActorList(List<MtimeFilmActorT> actorTList) {
        List<Actor> actorList = new ArrayList<>();
        for (MtimeFilmActorT mtimeFilmActorT : actorTList) {
            Actor actor = new Actor();
            Integer actor_id = mtimeFilmActorT.getActorId();
            String roleName = mtimeFilmActorT.getRoleName();
            actor.setRoleName(roleName);
            MtimeActorT actorT = getActorByActorId(actor_id);
            actor.setImgAddress(actorT.getActorImg());
            actor.setDirectorName(actorT.getActorName());
            actorList.add(actor);
        }
        return actorList;
    }

    private MtimeActorT getActorByActorId(Integer actor_id) {
        MtimeActorT mtimeActorT = new MtimeActorT();
        mtimeActorT.setUuid(actor_id);
        MtimeActorT actorT = mtimeActorTMapper.selectOne(mtimeActorT);
        return actorT;
    }

    private List<MtimeFilmActorT> getActorTListByFilmId(Integer id) {
        Wrapper<MtimeFilmActorT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_id", id);
        List<MtimeFilmActorT> actorTList = mtimeFilmActorTMapper.selectList(entityWrapper);
        return actorTList;
    }

    private Director getDirector(Integer directorId) {
        MtimeActorT directorInfo = mtimeActorTMapper.selectById(directorId);
        Director director = new Director(directorInfo.getActorImg(),directorInfo.getActorName());
        return director;
    }

    private String getFilmArea(Integer filmArea) {
        String filmAreaName = mtimesourceDictTMapper.selectById(filmArea).getShowName();
        return filmAreaName;
    }

    private MtimeHallFilmInfoT getHallFilmInfo(Integer id) {
        MtimeHallFilmInfoT mtimeHallFilmInfoT = new MtimeHallFilmInfoT();
        mtimeHallFilmInfoT.setFilmId(id);
        MtimeHallFilmInfoT hallFilmInfoT = mtimeHallFilmInfoTMapper.selectOne(mtimeHallFilmInfoT);
        return hallFilmInfoT;
    }

    private MtimeFilmInfoT getFilmInfoByFilmId(Integer id) {
        MtimeFilmInfoT mtimeFilmInfoT = new MtimeFilmInfoT();
        mtimeFilmInfoT.setFilmId(id+"");
        MtimeFilmInfoT filmInfo = mtimeFilmInfoTMapper.selectOne(mtimeFilmInfoT);
        return filmInfo;
    }

    private MtimeFilmT getFilmById(Integer id) {
        MtimeFilmT filmT = mtimeFilmTMapper.selectById(id);
        return filmT;
    }

    private List<FilmVo> convert2FilmDetailVo(List<MtimeFilmT> filmTList) {
        List<FilmVo> filmDetailVoList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(filmTList)){
            for (MtimeFilmT mtimeFilmT : filmTList) {
                FilmVo film = new FilmVo();
                film.setFilmId(mtimeFilmT.getUuid()+"");
                film.setFilmName(mtimeFilmT.getFilmName());
                film.setFilmScore(mtimeFilmT.getFilmScore());
                film.setFilmType(mtimeFilmT.getFilmType());
                film.setImgAddress(mtimeFilmT.getImgAddress());

                filmDetailVoList.add(film);
            }
        }
        return filmDetailVoList;
    }

}
