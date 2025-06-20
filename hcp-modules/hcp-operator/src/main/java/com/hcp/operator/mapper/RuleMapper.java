package com.hcp.operator.mapper;

import java.util.List;
import java.util.Map;

import com.hcp.operator.domain.Rule;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 收费规则Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface RuleMapper extends BaseMapperX<Rule>
{

    /**
     * 查询收费规则列表-分页
     *
     * @param rule 收费规则
     * @return 收费规则集合
     */
    IPage<Rule> selectRuleList(Page page,@Param("query") Rule rule);

    IPage<Rule> selectRuleListAll(Page page,@Param("query") Rule rule);


    /**
     * 查询收费规则列表
     *
     * @param rule 收费规则
     * @return 收费规则集合
     */
    List<Rule> selectRuleList(@Param("query") Rule rule);

    /**
     * 批量删除收费规则
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRuleByIds(Long[] ids);

    List<Map<String,Object>> getRulePriceListById(@Param("ruleId") int ruleId);

    int getRoleUseContById(@Param("id")int id);
}
