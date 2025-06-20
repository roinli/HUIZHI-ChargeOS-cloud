package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.City;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 省市管理Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface CityMapper extends BaseMapperX<City>
{

    /**
     * 查询省市管理列表-分页
     *
     * @param city 省市管理
     * @return 省市管理集合
     */
    IPage<City> selectCityList(Page page,@Param("query") City city);
    /**
     * 查询省市管理列表
     *
     * @param city 省市管理
     * @return 省市管理集合
     */
    List<City> selectCityList(@Param("query") City city);

    /**
     * 批量删除省市管理
     *
     * @param Ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCityByIds(Long[] Ids);
}
