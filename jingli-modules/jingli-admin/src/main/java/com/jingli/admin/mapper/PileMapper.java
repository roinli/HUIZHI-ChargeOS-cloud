package com.jingli.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PileMapper extends BaseMapper<ChargingPile> {

    /**
     * 功能描述：分页获取 充电桩列表
     *
     * @param page   分页信息
     * @param params 参数
     * @return java.util.List<com.jingli.modules.sys.pile.model.SysPileResult>
     * @Author: guoliangbo
     */
    List<SysPileResult> getPilePageList(Page page, @Param("params") Map<String, String> params);

    List<PlotPile> getPlotPile();

    /**
     * 获取充电桩信息
     *
     * @param page   分页信息
     * @param params 平台和代理商id
     * @return 充电桩信息
     */
    List<PilesDetail> selectPilesInfo(Page page, @Param("p") Map<String, Object> params);

    PileIncomeVO selectTotalByChargeNo(@Param("chargeNo") String chargeNo);
}
