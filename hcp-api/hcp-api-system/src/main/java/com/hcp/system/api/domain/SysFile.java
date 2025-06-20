package com.hcp.system.api.domain;

import lombok.Data;

/**
 * 文件信息
 *
 * @author vctgo
 */
@Data
public class SysFile
{
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

}
