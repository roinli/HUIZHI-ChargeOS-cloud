package com.hcp.demo.service.impl;

import java.util.List;

import com.hcp.common.datascope.annotation.DataScope;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.demo.domain.Demo;
import com.hcp.demo.mapper.DemoMapper;
import com.hcp.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 测试Service业务层处理
 *
 * @author vctgo
 * @date 2022-04-11
 */
@Service
public class DemoServiceImpl implements IDemoService
{
    @Autowired
    private DemoMapper demoMapper;

    /**
     * 查询测试
     *
     * @param demoId 测试主键
     * @return 测试
     */
    @Override
    public Demo selectDemoByDemoId(Long demoId)
    {
        return demoMapper.selectById(demoId);
    }

    /**
     * 查询测试列表-分页
     *
     * @param demo 测试
     * @return 测试
     */
    @Override
    @DataScope(deptAlias = "d")
    public IPage<Demo> selectDemoPage(Demo demo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return demoMapper.selectDemoList(mpPage, demo);
    }
    /**
     * 查询测试列表
     *
     * @param demo 测试
     * @return 测试
     */
    @Override
    public List<Demo> selectDemoList(Demo demo)
    {
        return demoMapper.selectDemoList(demo);
    }

    /**
     * 新增测试
     *
     * @param demo 测试
     * @return 结果
     */

    @Override
    public int insertDemo(Demo demo)
    {
        demo.setDeptId(SecurityUtils.getDeptId());
        return demoMapper.insert(demo);
    }

    /**
     * 修改测试
     *
     * @param demo 测试
     * @return 结果
     */
    @Override
    public int updateDemo(Demo demo)
    {
        return demoMapper.updateById(demo);
    }

    /**
     * 批量删除测试
     *
     * @param demoIds 需要删除的测试主键
     * @return 结果
     */
    @Override
    public int deleteDemoByDemoIds(Long[] demoIds)
    {
        return demoMapper.deleteDemoByDemoIds(demoIds);
    }

    /**
     * 删除测试信息
     *
     * @param demoId 测试主键
     * @return 结果
     */
    @Override
    public int deleteDemoByDemoId(Long demoId)
    {
        return demoMapper.deleteById(demoId);
    }
}
