package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.City;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 省市管理Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ICityService
{
    /**
     * 查询省市管理
     *
     * @param Id 省市管理主键
     * @return 省市管理
     */
    City selectCityById(Long Id);

    /**
     * 查询省市管理列表-分页
     *
     * @param city 省市管理
     * @return 省市管理集合
     */
    IPage<City> selectCityPage(City city);
    /**
     * 查询省市管理列表
     *
     * @param city 省市管理
     * @return 省市管理集合
     */
    List<City> selectCityList(City city);

    /**
     * 新增省市管理
     *
     * @param city 省市管理
     * @return 结果
     */
    int insertCity(City city);

    /**
     * 修改省市管理
     *
     * @param city 省市管理
     * @return 结果
     */
    int updateCity(City city);

    /**
     * 批量删除省市管理
     *
     * @param Ids 需要删除的省市管理主键集合
     * @return 结果
     */
    int deleteCityByIds(Long[] Ids);

    /**
     * 删除省市管理信息
     *
     * @param Id 省市管理主键
     * @return 结果
     */
    int deleteCityById(Long Id);
}
