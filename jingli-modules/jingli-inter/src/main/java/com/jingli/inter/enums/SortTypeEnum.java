package com.jingli.inter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: com.jingli.inter.enums.SortTypeEnum
 * @author: lv lu
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
