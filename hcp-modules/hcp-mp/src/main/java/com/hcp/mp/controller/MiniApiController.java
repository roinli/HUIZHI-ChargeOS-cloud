package com.hcp.mp.controller;

import com.hcp.common.core.domain.R;
import com.hcp.mp.service.IMiniappService;
import com.hcp.system.api.domain.Miniapp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mp")
@Slf4j
public class MiniApiController {
    @Autowired
    private IMiniappService miniappService;

    @GetMapping("/info/{appId}")
    R<Miniapp> getMiniAppInfo(@PathVariable("appId") String appId){
        return R.ok(miniappService.getById(appId));
    }
}
