package com.jingli.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.PlotModel;
import com.jingli.admin.model.PlotResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 小区Mapper
 *
 * @author yangbin
 */
@Repository
public interface PlotMapper extends BaseMapper<PlotModel> {

    /**
     * 查询站点信息
     *
     * @param params 参数
     * @param page   分页信息
     * @return 站点分页数据
     */
    List<PlotResult> getPlotList(@Param("p") Map<String, Object> params, Page<PlotResult> page);
}
