package com.hcp.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.SysOperLog;
import org.apache.ibatis.annotations.Param;

/**
 * 操作日志 数据层
 *
 * @author vctgo
 */
 public interface SysOperLogMapper extends BaseMapperX<SysOperLog>
{
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
     int insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合-分页
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    IPage<SysOperLog> selectOperLogList(Page page,@Param("query") SysOperLog operLog);
    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
     List<SysOperLog> selectOperLogList(@Param("query") SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
     int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
     SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
     void cleanOperLog();
}
