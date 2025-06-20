package com.hcp.mp.ro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "微信小程序用户手机请求接口")
@Data
public class BindPhoneRo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "code", description = "code")
    private String code;

    @Schema(name = "encryptedData", description = "加密数据")
    private String encryptedData;

    @Schema(name = "iv", description = "向量")
    private String iv;

}
