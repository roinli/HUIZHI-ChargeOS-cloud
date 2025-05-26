package com.hcp.operator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.MenberBalance;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import org.apache.ibatis.annotations.Param;
/**
 * 用户余额Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface MenberBalanceMapper extends BaseMapperX<MenberBalance>
{

    /**
     * 查询用户余额列表-分页
     *
     * @param menberBalance 用户余额
     * @return 用户余额集合
     */
    IPage<MenberBalance> selectMenberBalanceList(Page page,@Param("query") MenberBalance menberBalance);
    /**
     * 查询用户余额列表
     *
     * @param menberBalance 用户余额
     * @return 用户余额集合
     */
    @InterceptorIgnore(tenantLine = "1")
    List<MenberBalance> selectMenberBalanceList(@Param("query") MenberBalance menberBalance);

    /**
     * 批量删除用户余额
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMenberBalanceByIds(Long[] ids);

    @InterceptorIgnore(tenantLine = "1")
    MonthTotalRspVO queryMonthTotalByUserId(Long userId);
}
