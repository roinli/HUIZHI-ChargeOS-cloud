package com.hcp.mp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "基础请求信息")
@Data
public class SignRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "应用KEY", requiredMode = Schema.RequiredMode.AUTO)
    private String appid;

    @Schema(description = "当前时间戳 10位", requiredMode = Schema.RequiredMode.AUTO)
    private Long tm;

    @Schema(description = "签名\nsign拼接方式：所有参数排序(例如app_key=123&buy=1&cid=3),结尾直接追加app_secret值\nsign=md5(拼接串)", requiredMode = Schema.RequiredMode.AUTO)
    private String sign;

    @Schema(description = "随机字符串6-16位", requiredMode = Schema.RequiredMode.AUTO)
    private String noncecode;

    @Schema(description = "操作人ID，通过token换取", hidden = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonIgnore
    private Long opUid;

}
