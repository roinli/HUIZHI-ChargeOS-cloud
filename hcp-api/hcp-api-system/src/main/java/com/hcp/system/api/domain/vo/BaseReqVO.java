package com.hcp.system.api.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.jingli.chargeapi.vo.BaseReqVO
 * @author: lv lu
 * @create: 2023-11-13 22:43
 * @description: TODO
 */
@Data
public class BaseReqVO implements Serializable {

    private int pageNo = 1;

    private int pageSize = 10;


}
