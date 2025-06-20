package com.hcp.system.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PriceVO {
    private Integer id;

    /**
     * 费用，四轮车时是电费 元/度
     */
    private float price;
    /**
     * 服务费
     */
    private float servicePrice;

    /**
     * 时间范围,一个json字符串 [{"startTime":0.5,"endTime":1}]
     */
    private String rangeTime;

    private Integer hours;

    private Integer time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private float startTime;

    private float endTime;


}
