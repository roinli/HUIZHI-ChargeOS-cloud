package com.hcp.demo.service;

import java.util.List;

import com.hcp.demo.domain.Demo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 测试Service接口
 *
 * @author vctgo
 * @date 2022-04-11
 */
public interface IDemoService
{
    /**
     * 查询测试
     *
     * @param demoId 测试主键
     * @return 测试
     */
    Demo selectDemoByDemoId(Long demoId);

    /**
     * 查询测试列表-分页
     *
     * @param demo 测试
     * @return 测试集合
     */
    IPage<Demo> selectDemoPage(Demo demo);
    /**
     * 查询测试列表
     *
     * @param demo 测试
     * @return 测试集合
     */
    List<Demo> selectDemoList(Demo demo);

    /**
     * 新增测试
     *
     * @param demo 测试
     * @return 结果
     */
    int insertDemo(Demo demo);

    /**
     * 修改测试
     *
     * @param demo 测试
     * @return 结果
     */
    int updateDemo(Demo demo);

    /**
     * 批量删除测试
     *
     * @param demoIds 需要删除的测试主键集合
     * @return 结果
     */
    int deleteDemoByDemoIds(Long[] demoIds);

    /**
     * 删除测试信息
     *
     * @param demoId 测试主键
     * @return 结果
     */
    int deleteDemoByDemoId(Long demoId);
}
