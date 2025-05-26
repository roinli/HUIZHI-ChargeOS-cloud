package com.hcp.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 数据层
 *
 * @author vctgo
 */
 public interface SysNoticeMapper extends BaseMapperX<SysNotice>
{
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
     SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表-分页
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    IPage<SysNotice> selectNoticeList(Page page,@Param("query") SysNotice notice);
    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
     List<SysNotice> selectNoticeList(@Param("query") SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
     int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
     int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     *
     * @param noticeId 公告ID
     * @return 结果
     */
     int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
     int deleteNoticeByIds(Long[] noticeIds);
}
