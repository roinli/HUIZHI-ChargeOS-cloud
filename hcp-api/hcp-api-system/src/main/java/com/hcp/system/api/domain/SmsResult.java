package com.hcp.system.api.domain;

import lombok.Data;

/**
 * @description:
 * @author: xuchao
 * @time: 2022/4/18 13:18
 */
@Data
public class SmsResult {

    private String success;

    private String code;

    private String message;

    private String data;

    private String extras;

    private String timestamp;
}
