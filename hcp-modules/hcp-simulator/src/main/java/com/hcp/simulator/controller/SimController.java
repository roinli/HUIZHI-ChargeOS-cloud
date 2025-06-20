package com.hcp.simulator.controller;

import com.hcp.common.core.domain.R;
import com.hcp.common.core.exception.base.BaseException;
import com.hcp.simulator.common.SimCenter;
import com.hcp.simulator.dto.ChargingOrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/evcs/sim/v1")
@Tag(name = "模拟充电桩")
public class SimController {

    @Autowired
    private SimCenter simCenter;

    @Operation(summary = "启动模拟充电桩")
    @GetMapping("/start")
    public R<String> start(@Parameter(description = "充电桩ID") String pileId) {
        try {
            log.info("sim-api-start");
            if (StringUtils.isBlank(pileId)) {
                return R.fail("充电桩编号无效");
            }
            log.info("sim-api-start-check");
            if (simCenter.isOnline(pileId)) {
                return R.fail("充电桩已启动，请勿重复启动");
            }
            log.info("sim-api-start-run");
            simCenter.start(pileId);
        } catch (BaseException ex) {
            log.error("模拟桩启动失败", ex);
            return R.fail(ex.getDefaultMessage());
        }
        return R.ok("启动成功");
    }

    @Operation(summary = "停止模拟充电桩")
    @GetMapping("/stop")
    public R<String> stop(@Parameter(description = "充电桩ID") String pileId) {
        if (StringUtils.isBlank(pileId)) {
            return R.fail("充电桩编号无效");
        }
        try {
            simCenter.stop(pileId);
            return R.ok("停止成功");
        } catch (BaseException ex) {
            log.error("模拟桩停止失败", ex);
            return R.fail(ex.getDefaultMessage());
        }
    }

    @Operation(summary = "插枪")
    @GetMapping("/link")
    public R<String> link(@Parameter(description = "充电桩ID") String pileId,@Parameter(description = "端口-设备ID") String deviceId) {
        if (StringUtils.isBlank(pileId) || StringUtils.isBlank(deviceId)) {
            return R.fail("充电桩编号或设备编号无效");
        }
        try {
            simCenter.link(pileId, deviceId);
            return R.ok("插枪成功");
        } catch (BaseException ex) {
            log.error("模拟桩插枪失败", ex);
            return R.fail(ex.getDefaultMessage());
        }
    }

    @Operation(summary = "拔枪")
    @GetMapping("/unlink")
    public R<String> unLink(@Parameter(description = "充电桩ID") String pileId,@Parameter(description = "端口-设备ID") String deviceId) {
        if (StringUtils.isBlank(pileId) || StringUtils.isBlank(deviceId)) {
            return R.fail("充电桩编号或设备编号无效");
        }
        try {
            simCenter.unlink(pileId,deviceId);
            return R.ok("拔枪成功");
        } catch (BaseException ex) {
            log.error("模拟桩拔枪失败", ex);
            return R.fail(ex.getDefaultMessage());
        }
    }

    @Operation(summary = "开始充电")
    @PostMapping("/startCharge")
    public R<String> startCharge(@RequestBody ChargingOrderDTO chargingOrder) {
        if(chargingOrder == null){
            return R.fail("chargingOrder无效");
        }
        if (StringUtils.isBlank(chargingOrder.getPileId()) || StringUtils.isBlank(chargingOrder.getDeviceId())) {
            return R.fail("充电桩编号或设备编号无效");
        }
        try {
            simCenter.startCharge(chargingOrder);
            return R.ok("开始充电成功");
        } catch (BaseException e) {
            log.error("开始充电失败", e);
            return R.fail(e.getDefaultMessage());
        }
    }

    @Operation(summary = "停止充电")
    @GetMapping("/endCharge")
    public R<String> endCharge(@Parameter(description = "充电桩编号") String pileId,@Parameter(description = "设备-端口ID") String deviceId) {
        if (StringUtils.isBlank(pileId) || deviceId == null) {
            return R.fail("充电桩编号或设备编号无效");
        }
        try {
            simCenter.stopCharge(pileId,deviceId);
            return R.ok("停止充电成功");
        } catch (BaseException ex) {
            log.error("停止充电失败", ex);
            return R.fail(ex.getDefaultMessage());
        }
    }

}
