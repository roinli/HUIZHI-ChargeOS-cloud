package com.jingli.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.dataScope.DataScope;
import com.jingli.admin.mapper.PileMapper;
import com.jingli.admin.model.PileIncomeVO;
import com.jingli.admin.model.PlotPile;
import com.jingli.admin.model.SysPileResult;
import com.jingli.admin.service.PileService;
import com.jingli.admin.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChargingPileSericeImpl
 * @Description:
 * @Author 杨膑
 * @Version V1.0
 **/
@Service
public class PileSericeImpl implements PileService {
    @Autowired
    PlotService plotService;
    @Autowired
    private PileMapper chargingPileMapper;

    /**
     * @描述 获取充电桩数据
     * @参数 [page, params 查询参数]
     * @返回值 com.baomidou.mybatisplus.core.metadata.IPage<com.jingli.modules.wx.pile.model.PileResult>
     * @创建人 yangbin
     */
    @Override
    @DataScope(alias = "a.userId")
    public IPage<SysPileResult> getPage(Map<String, String> params, Page<SysPileResult> page) {
        // 分页查询充电桩列表数据
        List<SysPileResult> pilePageList = chargingPileMapper.getPilePageList(page, params);

        if (CollUtil.isNotEmpty(pilePageList)) {

            for (SysPileResult result : pilePageList) {
                PileIncomeVO pileIncomeVO = chargingPileMapper.selectTotalByChargeNo(result.getId());
                result.setPileIncomeVO(pileIncomeVO);
            }
        }


        page.setRecords(pilePageList);
        // 把查询到的数据组装成page类型
        return page;

    }

    @Override
    /**
     *@描述 获取首页小区地图充电桩数据
     *@参数 []
     *@返回值 java.util.List<com.jingli.modules.sys.map.model.PlotPile>
     *@创建人 yangbin
     */
    public List<PlotPile> getPlotPile() {
        return chargingPileMapper.getPlotPile();
    }

}
