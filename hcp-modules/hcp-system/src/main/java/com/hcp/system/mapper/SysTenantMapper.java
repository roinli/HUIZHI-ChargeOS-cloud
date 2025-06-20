package com.hcp.system.mapper;

import java.util.List;
import com.hcp.system.domain.SysTenant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 租户管理Mapper接口
 *
 * @author vctgo
 * @date 2022-04-11
 */
public interface SysTenantMapper extends BaseMapperX<SysTenant>
{

    /**
     * 查询租户管理列表-分页
     *
     * @param sysTenant 租户管理
     * @return 租户管理集合
     */
    IPage<SysTenant> selectSysTenantList(Page page,@Param("query") SysTenant sysTenant);
    /**
     * 查询租户管理列表
     *
     * @param sysTenant 租户管理
     * @return 租户管理集合
     */
    List<SysTenant> selectSysTenantList(@Param("query") SysTenant sysTenant);

    /**
     * 批量删除租户管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysTenantByIds(Long[] ids);
}
