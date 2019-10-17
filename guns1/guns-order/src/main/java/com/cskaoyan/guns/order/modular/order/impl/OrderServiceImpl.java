package com.cskaoyan.guns.order.modular.order.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cskaoyan.guns.order.persistence.dao.MoocOrderTMapper;
import com.cskaoyan.guns.order.persistence.dao.MtimeFieldTMapper;
import com.cskaoyan.guns.order.persistence.dao.MtimeHallDictTMapper;
import com.cskaoyan.guns.order.persistence.model.MoocOrderT;
import com.cskaoyan.guns.order.persistence.model.MtimeFieldT;
import com.cskaoyan.guns.order.persistence.model.MtimeHallDictT;
import com.cskaoyan.guns.rest.service.CinemaService;
import com.cskaoyan.guns.rest.service.FilmService;
import com.cskaoyan.guns.rest.service.OrderService;
import com.cskaoyan.guns.rest.service.UserService;
import com.cskaoyan.guns.rest.vo.orderVo.OrderRequestVo;
import com.cskaoyan.guns.rest.vo.orderVo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;


@Component
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    MoocOrderTMapper orderTMapper;

    @Autowired
    MtimeFieldTMapper fieldTMapper;

    @Autowired
    MtimeHallDictTMapper hallDictTMapper;

    @Reference(interfaceClass = FilmService.class)
    FilmService filmService;

    @Reference(interfaceClass = CinemaService.class)
    CinemaService cinemaService;

    @Reference(interfaceClass = UserService.class)
    UserService userService;

    @Override
    public TicketVo getTicketDetail(OrderRequestVo requestVo, String uuid) throws Exception {

        MtimeFieldT fieldT = getFieldById(requestVo.getFieldId());
        Integer cinemaId = fieldT.getCinemaId();
        Integer filmId = fieldT.getFilmId();
        Integer hallId = fieldT.getHallId();

        String filmName = filmService.getFilmNameByFilmId(filmId);

        String cinemaName = cinemaService.getCinemaNameById(cinemaId);

        MtimeHallDictT hallDictT = getHallDictById(hallId);
        String seatFile = hallDictT.getSeatAddress();

        String allSeatStr = getSeatInfoFromFile(seatFile);
        Integer[] soldSeats = requestVo.getSoldSeats();
        boolean seatAllow = isTrueSeats(soldSeats,allSeatStr);

        String order_uuid = UUID.randomUUID().toString().substring(0,18);

        Date date = new Date();

        MoocOrderT orderT = new MoocOrderT();
        if (seatAllow){
            orderT.setUuid(order_uuid);
            orderT.setCinemaId(cinemaId);
            orderT.setFieldId(fieldT.getUuid());
            orderT.setFilmId(filmId);
            orderT.setSeatsIds(Arrays.toString(soldSeats));
            orderT.setSeatsName(Arrays.toString(requestVo.getSeatsName()));
            orderT.setFilmPrice(fieldT.getPrice());
            orderT.setOrderPrice(orderT.getFilmPrice()*soldSeats.length);
            orderT.setOrderTime(date);
            orderT.setOrderUser(Integer.parseInt(uuid));
            orderT.setOrderStatus(0);
            orderTMapper.insert(orderT);
        }else {
            throw new Exception("座位不合法");
        }
        String seatsName = Arrays.toString(requestVo.getSeatsName());
        String timeStamp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 13);
        TicketVo ticketVo = setTicketVo(order_uuid,filmName,date,cinemaName,seatsName,orderT.getOrderPrice(),timeStamp);
        return ticketVo;
    }

    private TicketVo setTicketVo(String order_uuid, String filmName, Date date, String cinemaName, String seatsName, Integer orderPrice, String timeStamp) {
        TicketVo ticketVo = new TicketVo();
        ticketVo.setOrderId(order_uuid);
        ticketVo.setFilmName(filmName);
        ticketVo.setFieldTime(date);
        ticketVo.setCinemaName(cinemaName);
        ticketVo.setSeatsName(seatsName);
        ticketVo.setOrderPrice(orderPrice);
        ticketVo.setOrderTimestamp(timeStamp);
        return ticketVo;
    }

    private boolean isTrueSeats(Integer[] soldSeats, String allSeatStr) {
        boolean flag = true;
        for (Integer soldSeat : soldSeats) {
            String soldSeatStr = soldSeat+"";
            if (!allSeatStr.contains(soldSeatStr)){
                flag = false;
                break;
            }
        }
        return flag;
    }

    private MtimeHallDictT getHallDictById(Integer hallId) {
        MtimeHallDictT hallDictT = hallDictTMapper.selectById(hallId);
        return hallDictT;
    }

    private MtimeFieldT getFieldById(Integer fieldId) {
        MtimeFieldT fieldT = fieldTMapper.selectById(fieldId);
        return fieldT;
    }

    private String getSeatInfoFromFile(String file1) {
        String file2 = file1.replace("/","\\");
        String file = "F:\\wangdao_learning\\microservice\\project\\gunsProject\\guns1\\guns-order\\target\\classes\\" + file2;
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null ){
                if (str.contains("ids")) {
                    sb.append(str);
                    sb.append("/r/n");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
