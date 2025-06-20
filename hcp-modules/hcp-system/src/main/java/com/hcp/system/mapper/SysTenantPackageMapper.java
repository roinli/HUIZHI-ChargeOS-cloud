package com.hcp.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.common.mybatisplus.query.LambdaQueryWrapperX;
import com.hcp.system.domain.SysSimplePackage;
import com.hcp.system.domain.SysTenant;
import com.hcp.system.domain.SysTenantPackage;

/**
 * 租户套餐Mapper接口
 *
 * @author vctgo
 * @date 2022-03-25
 */
public interface SysTenantPackageMapper extends BaseMapperX<SysTenantPackage>
{
    /**
     * 查询租户套餐列表
     */
    default IPage<SysTenantPackage> selectSysTenantPackageList(SysTenantPackage sysTenantPackage) {
        return selectPage(new LambdaQueryWrapperX<SysTenantPackage>()
                .likeIfPresent(SysTenantPackage::getName,sysTenantPackage.getName())
                .eqIfPresent(SysTenantPackage::getStatus,sysTenantPackage.getStatus())
        );
    }

    /**
     * 导出租户套餐列表
     */
    default List<SysTenantPackage> selectSysTenantPackageExport(SysTenantPackage sysTenantPackage){
        return selectList(new LambdaQueryWrapperX<SysTenantPackage>()
                .likeIfPresent(SysTenantPackage::getName,sysTenantPackage.getName())
                .eqIfPresent(SysTenantPackage::getStatus,sysTenantPackage.getStatus()));
    }
    /**
     * 查询租户套餐精简列表
     *
     * @return 租户套餐
     */
    List<SysSimplePackage> getSimpleList();

    /**
     * 批量删除租户套餐
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysTenantPackageByIds(Long[] ids);

    /**
     * 根据租户套餐获取当前套餐有多少租户使用
     *
     * @return 租户列表
     */
    List<SysTenant> getTenantByPackage(Long tenantpackageid);


    /**
     * 根据租户套餐获取当前套餐有多少正常租户正在使用
     *
     * @return 租户列表
     */
    Integer getActiveTenantByPackage(Long tenantpackageid);

}
