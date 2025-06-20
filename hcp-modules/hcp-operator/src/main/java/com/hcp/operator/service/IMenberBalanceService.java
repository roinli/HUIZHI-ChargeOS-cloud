package com.hcp.operator.service;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.MenberBalance;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;

/**
 * 用户余额Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IMenberBalanceService
{
    /**
     * 查询用户余额
     *
     * @param id 用户余额主键
     * @return 用户余额
     */
    MenberBalance selectMenberBalanceById(Long id);

    /**
     * 查询用户余额列表-分页
     *
     * @param menberBalance 用户余额
     * @return 用户余额集合
     */
    IPage<MenberBalance> selectMenberBalancePage(MenberBalance menberBalance);
    /**
     * 查询用户余额列表
     *
     * @param menberBalance 用户余额
     * @return 用户余额集合
     */
    List<MenberBalance> selectMenberBalanceList(MenberBalance menberBalance);

    /**
     * 新增用户余额
     *
     * @param menberBalance 用户余额
     * @return 结果
     */
    int insertMenberBalance(MenberBalance menberBalance);

    /**
     * 修改用户余额
     *
     * @param menberBalance 用户余额
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    int updateMenberBalance(MenberBalance menberBalance);

    /**
     * 批量删除用户余额
     *
     * @param ids 需要删除的用户余额主键集合
     * @return 结果
     */
    int deleteMenberBalanceByIds(Long[] ids);

    /**
     * 删除用户余额信息
     *
     * @param id 用户余额主键
     * @return 结果
     */
    int deleteMenberBalanceById(Long id);

    MenberBalance getMenberBalanceByUserId(Long userId);

    MonthTotalRspVO queryMonthTotalByUserId(Long userId);
}
