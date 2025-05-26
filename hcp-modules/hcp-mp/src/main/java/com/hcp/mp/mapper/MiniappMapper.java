package com.hcp.mp.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.Miniapp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 小程序Mapper接口
 *
 * @author hcp
 * @date 2024-08-10
 */
public interface MiniappMapper extends BaseMapperX<Miniapp>
{

    /**
     * 查询小程序列表-分页
     *
     * @param miniapp 小程序
     * @return 小程序集合
     */
    IPage<Miniapp> selectMiniappList(Page page,@Param("query") Miniapp miniapp);
    /**
     * 查询小程序列表
     *
     * @param miniapp 小程序
     * @return 小程序集合
     */
    List<Miniapp> selectMiniappList(@Param("query") Miniapp miniapp);

    /**
     * 批量删除小程序
     *
     * @param appIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMiniappByAppIds(String[] appIds);

    //忽略租户获取小程序信息
    @InterceptorIgnore(tenantLine = "1")
    Miniapp getById(@Param("appId") String appId);
}
