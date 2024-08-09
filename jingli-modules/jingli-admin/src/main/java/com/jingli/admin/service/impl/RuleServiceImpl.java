
package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.dataScope.DataScope;
import com.jingli.admin.mapper.RuleMapper;
import com.jingli.admin.model.Rule;
import com.jingli.admin.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RuleServiceImpl
 * @Description: 规则管理实现方法
 * @Author yangbin
 * @Version V1.0
 **/
@Service
public class RuleServiceImpl implements RuleService {

    private static final Logger log = LoggerFactory.getLogger(RuleServiceImpl.class);

    @Autowired
    private RuleMapper ruleMapper;


    /**
     * @描述 查询规则列表
     * @参数 [params 查询参数]
     * @返回值 java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @创建人 yangbin
     */
    @Override
    @DataScope(alias = "a.userId")
    public IPage<Rule> getRuleListByUserNm(Map<String, Object> params, Page<Rule> page) {
        // 通过用户名获取规则列表数据
        List<Rule> ruleList = ruleMapper.getRuleListByUserNm(params, page);
        page.setRecords(ruleList);
        return page;
    }
}