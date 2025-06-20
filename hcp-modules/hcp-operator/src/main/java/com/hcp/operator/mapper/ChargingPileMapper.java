package com.hcp.operator.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.operator.domain.PileTotalResult;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.system.api.domain.ChargingPile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.SysDept;
import com.hcp.system.api.domain.vo.*;
import org.apache.ibatis.annotations.Param;
/**
 * 充电桩Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ChargingPileMapper extends BaseMapperX<ChargingPile>
{

    /**
     * 查询充电桩列表-分页
     *
     * @param chargingPile 充电桩
     * @return 充电桩集合
     */
    IPage<ChargingPile> selectChargingPileList(Page page,@Param("query") ChargingPile chargingPile);
    /**
     * 查询充电桩列表
     *
     * @param chargingPile 充电桩
     * @return 充电桩集合
     */
    List<ChargingPile> selectChargingPileList(@Param("query") ChargingPile chargingPile);

    /**
     * 批量删除充电桩
     *
     * @param pileIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteChargingPileByPileIds(String[] pileIds);

    IPage<PileTotalResult>  getChargeTotal(Page page, @Param("pileVo") QueryChargePileVo vo);

    List<PileTotalResult>  getChargeTotal( @Param("pileVo") QueryChargePileVo vo);

    //忽略租户查询桩信息
    @InterceptorIgnore(tenantLine = "1")
    ChargingPile getById(@Param("pileId") String pileId);

    List<PlotVO> queryPlotInfoByVo(PlotInfoReqVO plotInfoReqVO);

    @InterceptorIgnore(tenantLine = "1")
    Page<PlotVO> queryPlotInfoByPage(@Param("page") Page page,@Param("plotInfoReqVO") PlotInfoReqVO plotInfoReqVO);

    ChargingPileVO queryChargingPileData(String pileId);
    @InterceptorIgnore(tenantLine = "1")
    PlotDetailVo queryPlotDetailById(String plotId);
    @InterceptorIgnore(tenantLine = "1")
    List<PlotPileVo> queryPileListById(@Param("map") HashMap<String, String> map);
    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int insertPile(ChargingPile record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(ChargingPile record);
    @InterceptorIgnore(tenantLine = "1")
    ChargingPile selectByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(ChargingPile record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(ChargingPile record);
}
