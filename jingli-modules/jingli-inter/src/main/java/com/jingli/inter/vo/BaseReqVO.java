package com.jingli.inter.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReqVO implements Serializable {

    private int pageNo = 1;

    private int pageSize = 10;


}
