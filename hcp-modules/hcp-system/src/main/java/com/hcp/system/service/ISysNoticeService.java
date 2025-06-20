package com.hcp.system.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.domain.SysNotice;

/**
 * 公告 服务层
 *
 * @author vctgo
 */
 public interface ISysNoticeService
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
    IPage<SysNotice> selectNoticePage(SysNotice notice);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
     List<SysNotice> selectNoticeList(SysNotice notice);

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
     * 删除公告信息
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
