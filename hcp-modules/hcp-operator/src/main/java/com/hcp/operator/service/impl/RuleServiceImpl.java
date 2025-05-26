package com.hcp.operator.service.impl;

import java.util.*;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hcp.common.core.exception.ServiceException;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.operator.domain.CustomPrice;
import com.hcp.operator.domain.PriceVo;
import com.hcp.operator.mapper.CustomPriceMapper;
import com.hcp.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.RuleMapper;
import com.hcp.operator.domain.Rule;
import com.hcp.operator.service.IRuleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收费规则Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class RuleServiceImpl implements IRuleService
{
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private CustomPriceMapper customPriceMapper;

    /**
     * 默认规则
     */
    public static final Integer RULE_DEFAULT = 1;

    /**
     * 查询收费规则
     *
     * @param id 收费规则主键
     * @return 收费规则
     */
    @Override
    public Rule selectRuleById(Long id)
    {
        return ruleMapper.selectById(id);
    }

    /**
     * 查询收费规则列表-分页
     *
     * @param rule 收费规则
     * @return 收费规则
     */
    @Override
    public IPage<Rule> selectRulePage(Rule rule)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        Long userId = SecurityUtils.getUserId();
        // 非管理员或未选择用户 则查询当前用户数据
        if (!SysUser.isAdmin(userId))
        {
            rule.setUserId(userId);
        }

        return ruleMapper.selectRuleListAll(mpPage,rule);
    }
    /**
     * 查询收费规则列表
     *
     * @param rule 收费规则
     * @return 收费规则
     */
    @Override
    public List<Rule> selectRuleList(Rule rule)
    {
        return ruleMapper.selectRuleList(rule);
    }

    /**
     * 新增收费规则
     *
     * @param rule 收费规则
     * @return 结果
     */

    @Override
    public int insertRule(Rule rule)
    {
        return ruleMapper.insert(rule);
    }

    /**
     * 修改收费规则
     *
     * @param rule 收费规则
     * @return 结果
     */
    @Override
    public int updateRule(Rule rule)
    {
        return ruleMapper.updateById(rule);
    }

    /**
     * 批量删除收费规则
     *
     * @param ids 需要删除的收费规则主键
     * @return 结果
     */
    @Override
    public int deleteRuleByIds(Long[] ids)
    {
        return ruleMapper.deleteRuleByIds(ids);
    }



    /**
     * 删除收费规则信息
     *
     * @param id 收费规则主键
     * @return 结果
     */
    @Override
    public int deleteRuleById(Long id)
    {
        return ruleMapper.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getRulePriceListById(int ruleId) {
        return ruleMapper.getRulePriceListById(ruleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addRule4Four(Rule rule) {
        // 1.0 校验时间规则
        checkRangeTimeAndName(rule, (short) 0);
        // 2.0 插入规则
        Rule insertRule = new Rule();
        BeanUtil.copyProperties(rule,insertRule);
        ruleMapper.insert(insertRule);
        // 3.0 插入价格
        Integer ruleId = Math.toIntExact(insertRule.getId());
        List<PriceVo> priceVoList = rule.getPriceList();
        for (PriceVo price : priceVoList) {
            String rangTime = JSONUtil.toJsonStr(price.getTimeRangeList());
            CustomPrice customPrice = CustomPrice.builder().ruleId(String.valueOf(ruleId))
                    .rangTime(rangTime).build();
            BeanUtil.copyProperties(price, customPrice);
            customPriceMapper.insert(customPrice);
        }
        return AjaxResult.success("新建规则成功");
    }

    /**
     * 校验数据
     * @param rule 规则参数
     * @param handleType 操作类型 0:新增 1:编辑
     */
    private void checkRangeTimeAndName(Rule rule,short handleType) {
        List<PriceVo> priceVoList = rule.getPriceList();
        List<PriceVo.TimeRange>  allTimes = new ArrayList<>();
        for (PriceVo price : priceVoList) {
            List<PriceVo.TimeRange>  timeRanges = price.getTimeRangeList();
            allTimes.addAll(timeRanges);
            for (PriceVo.TimeRange timeRange : timeRanges) {
                float startTime = timeRange.getStartTime();
                if(startTime >= timeRange.getEndTime()){
                    throw new ServiceException("开始时间不能大于等于结束时间！");
                }
            }
        }
        // 对时间进行排序
        allTimes.sort(Comparator.comparing(PriceVo.TimeRange::getStartTime));
        float firstStartTime = allTimes.get(0).getStartTime();
        if(firstStartTime != 0){
            throw new ServiceException("设置的起始时间不是0点！");
        }
        float maxValue = 0;
        for (PriceVo.TimeRange allTime : allTimes) {
            float startTime = allTime.getStartTime();
            float endTime = allTime.getEndTime();
            if(startTime == maxValue){
                maxValue = endTime;
            } else {
                throw new ServiceException("设置的时间不连贯！");
            }
        }
        if(maxValue < 24){
            throw new ServiceException("设置的结束时间没有到24点！");
        }
        String handle = handleType == 0 ? "新增" : "编辑";
//        Integer userId = rule.getUserId();
//        // 1.1 判断当前代理商规则名称是否重复
//        Integer existNum = ruleMapper.checkNameExist(rule.getChangeName(),userId,rule.getId());
//        // 如果重复 不做插入操作 并提示重复
//        if (existNum > 0) {
//            log.error("规则" + handle + "失败，规则名称重复!");
//            throw new ServiceException(handle + "失败，规则名称重复！");
//        }
    }

    public AjaxResult copyRule(Rule rule) {
        Rule oldRule = ruleMapper.selectById(rule.getId());
        oldRule.setChangeName(rule.getChangeName());
        oldRule.setId(null);
        oldRule.setUserId(rule.getUserId());
        /*复制规则*/
        /*如果重复 不做插入操作 并提示重复*/
        if (!ruleMapper.selectRuleList(rule).isEmpty() && ruleMapper.selectRuleList(rule).get(0) != null) {
            return AjaxResult.error(-1, "复制失败，规则名称重复！");
        }

        //设置为非默认规则
        oldRule.setIsGive(0);
        oldRule.setIsSysGive(0);
        oldRule.setCreateTime(new Date());
        // 插入规则
        ruleMapper.insert(oldRule);

        QueryWrapper<CustomPrice> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rule_id", oldRule.getId());
        // 复制规则价格
        List<CustomPrice> customPrices = customPriceMapper.selectList(queryWrapper);
        if (customPrices.size() > 0) {
            for (CustomPrice c : customPrices) {
                c.setPileId(null);
                c.setRuleId(String.valueOf(oldRule.getId()));
                c.setCreateTime(new Date());
                customPriceMapper.insert(c);
            }
        }
        return AjaxResult.success("复制成功");
    }

    @Override
    public AjaxResult deleteRule(int id) {
        /*获取当前规则下使用规则的是充电桩数量*/
        int useCount = ruleMapper.getRoleUseContById(id);
        /*如果使用该规则数量大于0  不做删除操作，并提示用户*/
        if (useCount > 0) {
            return AjaxResult.error(-1, "删除失败，有" + useCount + "个充电桩在使用该规则");
        }
        // 校验默认规则不可删除
        Rule rule = ruleMapper.selectById(id);
        if (null == rule) {
            return AjaxResult.error(-1, "该规则ID不存在！");
        } else if (rule.getIsGive()==1 || rule.getIsSysGive() == 1){
            return AjaxResult.error("默认规则不可删除");
        }else  {
            Integer isSysGive = rule.getIsSysGive();
            Integer isGive = rule.getIsGive();
            if (RULE_DEFAULT.equals(isSysGive) || RULE_DEFAULT.equals(isGive)) {
                return AjaxResult.error(-2, "默认规则不可删除！");
            }
            /*删除规则*/
            ruleMapper.deleteById(id);
            return AjaxResult.success("删除成功！");
        }

    }
}
