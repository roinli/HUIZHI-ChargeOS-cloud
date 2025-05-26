package com.hcp.operator.service.impl;

import java.util.Collections;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.MenberBalanceMapper;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.operator.service.IMenberBalanceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 用户余额Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class MenberBalanceServiceImpl implements IMenberBalanceService
{
    @Autowired
    private MenberBalanceMapper menberBalanceMapper;

    /**
     * 查询用户余额
     *
     * @param id 用户余额主键
     * @return 用户余额
     */
    @Override
    public MenberBalance selectMenberBalanceById(Long id)
    {
        return menberBalanceMapper.selectById(id);
    }

    /**
     * 查询用户余额列表-分页
     *
     * @param menberBalance 用户余额
     * @return 用户余额
     */
    @Override
    public IPage<MenberBalance> selectMenberBalancePage(MenberBalance menberBalance)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return menberBalanceMapper.selectMenberBalanceList(mpPage,menberBalance);
    }
    /**
     * 查询用户余额列表
     *
     * @param menberBalance 用户余额
     * @return 用户余额
     */
    @Override
    public List<MenberBalance> selectMenberBalanceList(MenberBalance menberBalance)
    {
        return menberBalanceMapper.selectMenberBalanceList(menberBalance);
    }

    /**
     * 新增用户余额
     *
     * @param menberBalance 用户余额
     * @return 结果
     */

    @Override
    public int insertMenberBalance(MenberBalance menberBalance)
    {
        return menberBalanceMapper.insert(menberBalance);
    }

    /**
     * 修改用户余额
     *
     * @param menberBalance 用户余额
     * @return 结果
     */
    @Override
    public int updateMenberBalance(MenberBalance menberBalance)
    {
        return menberBalanceMapper.updateById(menberBalance);
    }

    /**
     * 批量删除用户余额
     *
     * @param ids 需要删除的用户余额主键
     * @return 结果
     */
    @Override
    public int deleteMenberBalanceByIds(Long[] ids)
    {
        return menberBalanceMapper.deleteMenberBalanceByIds(ids);
    }

    /**
     * 删除用户余额信息
     *
     * @param id 用户余额主键
     * @return 结果
     */
    @Override
    public int deleteMenberBalanceById(Long id)
    {
        return menberBalanceMapper.deleteById(id);
    }

    @Override
    public MenberBalance getMenberBalanceByUserId(Long userId) {
        MenberBalance menberBalance = new MenberBalance();
        menberBalance.setMemberId(userId);
        List<MenberBalance> list = this.selectMenberBalanceList(menberBalance);
        if (CollUtil.isNotEmpty(list)){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public MonthTotalRspVO queryMonthTotalByUserId(Long userId) {
        return menberBalanceMapper.queryMonthTotalByUserId(userId);
    }
}
