package com.hcp.modules.monitor.domain;

import lombok.Data;

/**
 * @author: vctgo
 * @date: 2022/4/26 10:06
 * @description:
 */
@Data
public class ConnConfig {

    /**
     * ip地址
     */
    private String ip;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String userpwd;

    /**
     * 端口
     */
    private Integer port;


}
