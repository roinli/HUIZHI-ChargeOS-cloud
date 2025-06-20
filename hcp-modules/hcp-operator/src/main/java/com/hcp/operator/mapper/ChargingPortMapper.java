package com.hcp.operator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.domain.vo.PilePortVO;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import org.apache.ibatis.annotations.Param;
/**
 * 充电桩端口Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ChargingPortMapper extends BaseMapperX<ChargingPort>
{

    /**
     * 查询充电桩端口列表-分页
     *
     * @param chargingPort 充电桩端口
     * @return 充电桩端口集合
     */
    IPage<ChargingPort> selectChargingPortList(Page page,@Param("query") ChargingPort chargingPort);
    /**
     * 查询充电桩端口列表
     *
     * @param chargingPort 充电桩端口
     * @return 充电桩端口集合
     */
    List<ChargingPort> selectChargingPortList(@Param("query") ChargingPort chargingPort);

    /**
     * 批量删除充电桩端口
     *
     * @param portIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteChargingPortByPortIds(Long[] portIds);

    //忽略租户获取端口信息
    @InterceptorIgnore(tenantLine = "1")
    ChargingPort selectPort(@Param("pileId") String pileId, @Param("port")String port);
    @InterceptorIgnore(tenantLine = "1")
    List<PilePortVO> queryPortInfoVo(@Param("plotInfoReqVO") PlotInfoReqVO plotInfoReqVO);

    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int insertPort(ChargingPort record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(ChargingPort record);
    @InterceptorIgnore(tenantLine = "1")
    ChargingPort selectByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(ChargingPort record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(ChargingPort record);
}
