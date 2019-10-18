package com.cskaoyan.guns.rest.vo.orderVo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class TicketVo implements Serializable {
    private static final long serialVersionUID = 4565803859322503111L;
    /*
        "data":{
            "orderId":"18392981493",
            "filmName":"基于SpringBoot 十分钟搞定后台管理平台",
            "fieldTime":"今天 9月8号 11:50",
            "cinemaName":"万达影城(顺义金街店)",
            "seatsName":"1排3座 1排4座 2排4座",
            "orderPrice":"120",
            "orderTimestamp":"1589754126"
        }
         */
    private String orderId;
    private String filmName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fieldTime;
    private String cinemaName;
    private String seatsName;
    private Integer orderPrice;
    private String orderTimestamp;
}
