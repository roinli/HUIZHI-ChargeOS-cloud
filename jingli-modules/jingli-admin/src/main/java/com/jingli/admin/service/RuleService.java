
package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.Rule;

import java.util.Map;


/**
 * @ClassName Rule
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
public interface RuleService {
    /**
     * 根据代理商用户名查询规则列表
     *
     * @param params 参数
     * @return 分页结果
     */
    IPage<Rule> getRuleListByUserNm(Map<String, Object> params, Page<Rule> page);
}