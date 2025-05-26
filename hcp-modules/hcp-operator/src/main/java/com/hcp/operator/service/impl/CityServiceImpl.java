package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.CityMapper;
import com.hcp.operator.domain.City;
import com.hcp.operator.service.ICityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 省市管理Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class CityServiceImpl implements ICityService
{
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询省市管理
     *
     * @param Id 省市管理主键
     * @return 省市管理
     */
    @Override
    public City selectCityById(Long Id)
    {
        return cityMapper.selectById(Id);
    }

    /**
     * 查询省市管理列表-分页
     *
     * @param city 省市管理
     * @return 省市管理
     */
    @Override
    public IPage<City> selectCityPage(City city)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return cityMapper.selectCityList(mpPage,city);
    }
    /**
     * 查询省市管理列表
     *
     * @param city 省市管理
     * @return 省市管理
     */
    @Override
    public List<City> selectCityList(City city)
    {
        return cityMapper.selectCityList(city);
    }

    /**
     * 新增省市管理
     *
     * @param city 省市管理
     * @return 结果
     */

    @Override
    public int insertCity(City city)
    {
        return cityMapper.insert(city);
    }

    /**
     * 修改省市管理
     *
     * @param city 省市管理
     * @return 结果
     */
    @Override
    public int updateCity(City city)
    {
        return cityMapper.updateById(city);
    }

    /**
     * 批量删除省市管理
     *
     * @param Ids 需要删除的省市管理主键
     * @return 结果
     */
    @Override
    public int deleteCityByIds(Long[] Ids)
    {
        return cityMapper.deleteCityByIds(Ids);
    }

    /**
     * 删除省市管理信息
     *
     * @param Id 省市管理主键
     * @return 结果
     */
    @Override
    public int deleteCityById(Long Id)
    {
        return cityMapper.deleteById(Id);
    }
}
