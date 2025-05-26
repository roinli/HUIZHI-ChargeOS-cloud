package com.hcp.operator.service;

import java.util.List;
import java.util.Map;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.operator.domain.Rule;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 收费规则Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IRuleService
{
    /**
     * 查询收费规则
     *
     * @param id 收费规则主键
     * @return 收费规则
     */
    Rule selectRuleById(Long id);

    /**
     * 查询收费规则列表-分页
     *
     * @param rule 收费规则
     * @return 收费规则集合
     */
    IPage<Rule> selectRulePage(Rule rule);
    /**
     * 查询收费规则列表
     *
     * @param rule 收费规则
     * @return 收费规则集合
     */
    List<Rule> selectRuleList(Rule rule);

    /**
     * 新增收费规则
     *
     * @param rule 收费规则
     * @return 结果
     */
    int insertRule(Rule rule);


    AjaxResult addRule4Four(Rule rule);

    /**
     * 修改收费规则
     *
     * @param rule 收费规则
     * @return 结果
     */
    int updateRule(Rule rule);

    /**
     * 批量删除收费规则
     *
     * @param ids 需要删除的收费规则主键集合
     * @return 结果
     */
    int deleteRuleByIds(Long[] ids);

    /**
     * 删除收费规则信息
     *
     * @param id 收费规则主键
     * @return 结果
     */
    int deleteRuleById(Long id);

    public List<Map<String, Object>> getRulePriceListById(int ruleId);

    public AjaxResult copyRule(Rule rule);

    public AjaxResult deleteRule(int id);
}
