package com.cskaoyan;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.guns.cinema.GunsCinemaApplication;
import com.cskaoyan.guns.cinema.common.persistence.dao.MtimeCinemaTMapper;
import com.cskaoyan.guns.cinema.common.persistence.model.MtimeCinemaT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GunsCinemaApplication.class)
@RunWith(
        SpringRunner.class
)
public class CinemaTest {

    @Autowired
    private MtimeCinemaTMapper mapper;
    @Test
    public void test01(){
        MtimeCinemaT cinemaInfos = mapper.getCinemaInfos(1);

        System.out.println(JSON.toJSONString(cinemaInfos));
    }
}
