package com.hcp.mp.service;

import java.util.List;
import com.hcp.system.api.domain.Miniapp;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 小程序Service接口
 *
 * @author hcp
 * @date 2024-08-10
 */
public interface IMiniappService
{
    /**
     * 查询小程序
     *
     * @param appId 小程序主键
     * @return 小程序
     */
    Miniapp selectMiniappByAppId(String appId);

    /**
     * 查询小程序列表-分页
     *
     * @param miniapp 小程序
     * @return 小程序集合
     */
    IPage<Miniapp> selectMiniappPage(Miniapp miniapp);
    /**
     * 查询小程序列表
     *
     * @param miniapp 小程序
     * @return 小程序集合
     */
    List<Miniapp> selectMiniappList(Miniapp miniapp);

    /**
     * 新增小程序
     *
     * @param miniapp 小程序
     * @return 结果
     */
    int insertMiniapp(Miniapp miniapp);

    /**
     * 修改小程序
     *
     * @param miniapp 小程序
     * @return 结果
     */
    int updateMiniapp(Miniapp miniapp);

    /**
     * 批量删除小程序
     *
     * @param appIds 需要删除的小程序主键集合
     * @return 结果
     */
    int deleteMiniappByAppIds(String[] appIds);

    /**
     * 删除小程序信息
     *
     * @param appId 小程序主键
     * @return 结果
     */
    int deleteMiniappByAppId(String appId);

    Miniapp getById(String id);
}
