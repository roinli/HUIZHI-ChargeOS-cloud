package com.jingli.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.Rule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @ClassName RuleMapper
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
@Repository
public interface RuleMapper extends BaseMapper<Rule> {

    List<Rule> getRuleListByUserNm(@Param("p") Map<String, Object> params, Page<Rule> page);

}

