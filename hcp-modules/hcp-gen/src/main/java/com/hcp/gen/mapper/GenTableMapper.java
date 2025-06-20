package com.hcp.gen.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.gen.domain.GenTable;
import org.apache.ibatis.annotations.Param;

/**
 * 业务 数据层
 *
 * @author vctgo
 */
 public interface GenTableMapper
{
    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
     @InterceptorIgnore(tenantLine = "1")
     IPage<GenTable> selectGenTableList(Page page, @Param("query") GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @InterceptorIgnore(tenantLine = "1")
    IPage<GenTable> selectDbTableList(Page page,@Param("query") GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
     @InterceptorIgnore(tenantLine = "1")
     List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    @InterceptorIgnore(tenantLine = "1")
     List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @InterceptorIgnore(tenantLine = "1")
     GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    @InterceptorIgnore(tenantLine = "1")
     GenTable selectGenTableByName(String tableName);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
     int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
     int updateGenTable(GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
     int deleteGenTableByIds(Long[] ids);
}
