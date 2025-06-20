package com.hcp.gen.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.gen.domain.GenTableColumn;
import org.apache.ibatis.annotations.Param;

/**
 * 业务字段 数据层
 *
 * @author vctgo
 */
 public interface GenTableColumnMapper
{
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
     @InterceptorIgnore(tenantLine = "1")
     List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 查询业务字段列表-分页
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @InterceptorIgnore(tenantLine = "1")
     IPage<GenTableColumn> selectGenTableColumnListByTableId(Page page, @Param("tableId") Long tableId);

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @InterceptorIgnore(tenantLine = "1")
    List<GenTableColumn> selectGenTableColumnListByTableId(@Param("tableId") Long tableId);

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
     @InterceptorIgnore(tenantLine = "1")
     int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
     @InterceptorIgnore(tenantLine = "1")
     int updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 删除业务字段
     *
     * @param genTableColumns 列数据
     * @return 结果
     */
     @InterceptorIgnore(tenantLine = "1")
     int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

    /**
     * 批量删除业务字段
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     @InterceptorIgnore(tenantLine = "1")
     int deleteGenTableColumnByIds(Long[] ids);
}
