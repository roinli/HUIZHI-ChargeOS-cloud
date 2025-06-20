package com.hcp.operator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: com.jingli.chargeapi.enums.SortTypeEnum
 * @author: lv lu
 * @create: 2023-11-13 22:51
 * @description: TODO
 */
@AllArgsConstructor
@Getter
public enum SortTypeEnum {

    ONE("1","距离"),
    TWO("2","价格"),
    THREE("3","智能");

    private String code;

    private String message;
}
