package com.hcp.mp.vo;

import com.hcp.system.api.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户登录信息")
@Data
public class UserVo {
    private static final long serialVersionUID = 1L;

    @Schema(description = "登录token")
    private String token;

    @Schema(description = "用户信息")
    private Member member;
}
