package com.hcp.system.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.common.core.constant.CacheConstants;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.StringUtils;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.redis.service.RedisService;
import com.hcp.system.api.domain.SysRole;
import com.hcp.system.api.model.LoginUser;
import com.hcp.system.domain.SysRoleMenu;
import com.hcp.system.domain.SysSimplePackage;
import com.hcp.system.domain.SysTenant;
import com.hcp.system.mapper.SysRoleMapper;
import com.hcp.system.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.system.mapper.SysTenantPackageMapper;
import com.hcp.system.domain.SysTenantPackage;
import com.hcp.system.service.ISysTenantPackageService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 租户套餐Service业务层处理
 *
 * @author vctgo
 * @date 2022-03-25
 */
@Service
public class SysTenantPackageServiceImpl implements ISysTenantPackageService
{
    @Autowired
    private SysTenantPackageMapper sysTenantPackageMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询租户套餐
     *
     * @param id 租户套餐主键
     * @return 租户套餐
     */
    @Override
    public SysTenantPackage selectSysTenantPackageById(Long id)
    {
        return sysTenantPackageMapper.selectById(id);
    }

    /**
     * 查询租户套餐精简列表
     */
    @Override
    public List<SysSimplePackage> getSimpleList() {
        return sysTenantPackageMapper.getSimpleList();
    }

    /**
     * 查询租户套餐列表
     *
     * @param sysTenantPackage 租户套餐
     * @return 租户套餐
     */
    @Override
    public IPage<SysTenantPackage> selectSysTenantPackageList(SysTenantPackage sysTenantPackage)
    {
        return sysTenantPackageMapper.selectSysTenantPackageList(sysTenantPackage);
    }

    /**
     * 新增租户套餐
     *
     * @param sysTenantPackage 租户套餐
     * @return 结果
     */
    @Override
    public int insertSysTenantPackage(SysTenantPackage sysTenantPackage)
    {
        return sysTenantPackageMapper.insert(sysTenantPackage);
    }

    /**
     * 修改租户套餐
     *
     * @param sysTenantPackage 租户套餐
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateSysTenantPackage(SysTenantPackage sysTenantPackage)
    {
        //判断是否有 租户使用此套餐
        if(sysTenantPackage.getStatus() == 1){
           Integer activeTenants =  sysTenantPackageMapper.getActiveTenantByPackage(sysTenantPackage.getId());
           if (activeTenants > 0){
               //目前有正常的租户在使用此套餐，不允许关闭套餐
              return AjaxResult.error("租户套餐已经被使用，无法被停用！");
           }
        }
        //租户套餐修改逻辑优化,踢掉当前所有登陆此套餐的用户
        //先查询租户套餐有没有被改变
        SysTenantPackage old  = sysTenantPackageMapper.selectById(sysTenantPackage.getId());
        Map<String, String[]>  packageIsChange = StringUtils.CompareStringArray(old.getMenuIds().split(","),sysTenantPackage.getMenuIds().split(","));
        boolean excludeUser = packageIsChange.get("deleteArr").length>0 && packageIsChange.get("deleteArr") !=null;
        boolean addUserMenu = packageIsChange.get("addArr").length>0 && packageIsChange.get("addArr") !=null;
        if (excludeUser||addUserMenu ){
            //查询当前套餐跟哪些租户做了绑定
            List<SysTenant> tenants =  sysTenantPackageMapper.getTenantByPackage(sysTenantPackage.getId());
            tenants.stream().forEach(tenant->{
                //先删除被去掉的菜单
                if (excludeUser){
                    sysRoleMenuMapper.deleteRoleMenuByTenantIdAndPackage(tenant.getId(),StringUtils.StringToLong(packageIsChange.get("deleteArr")));
                }
                //只给租户管理员添加新增的菜单
                if (addUserMenu){
                    //查询当前租户的管理员角色id
                    SysRole role = sysRoleMapper.selectRoleByTenant(tenant.getId());
                    List<SysRoleMenu> roleMenuList = Arrays.asList(packageIsChange.get("addArr")).stream().map(menuid -> {
                        SysRoleMenu entity = new SysRoleMenu();
                        entity.setRoleId(role.getRoleId());
                        entity.setMenuId(Convert.toLong(menuid));
                        entity.setTenantId(tenant.getId());
                        return entity;
                    }).collect(Collectors.toList());
                    sysRoleMenuMapper.batchRoleMenuByPackage(roleMenuList);
                }
                //踢掉当前租户下的用户
                //只有原始套餐有菜单被删除的时候才会涉及到用户权限问题
                if(excludeUser){
                    //踢掉当前用户,刷新已有权限
                    // TODO  此处需要优化,如果当前同时在线人数较多,会出现卡顿
                    Collection<String> keys = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
                    for (String key : keys)
                    {
                        LoginUser onlineUser = redisService.getCacheObject(key);
                        if(onlineUser.getSysUser().getTenantId() != null && onlineUser.getSysUser().getTenantId().equals(tenant.getId()))
                        {
                            redisService.deleteObject(key);
                        }
                    }
                }
            });
        }
        return AjaxResult.success(sysTenantPackageMapper.updateById(sysTenantPackage));
    }

    /**
     * 批量删除租户套餐
     *
     * @param ids 需要删除的租户套餐主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteSysTenantPackageByIds(Long[] ids)
    {
        //判断是否有 租户使用此套餐
        Integer activeTenants =  sysTenantPackageMapper.getActiveTenantByPackage(ids[0]);
        if (activeTenants > 0){
            //目前有正常的租户在使用此套餐，不允许关闭套餐
            return AjaxResult.error("租户套餐已经被使用，无法被删除！");
        }
        return AjaxResult.success(sysTenantPackageMapper.deleteSysTenantPackageByIds(ids));
    }

    /**
     * 查询导出
     * @return 租户套餐
     */
    @Override
    public List<SysTenantPackage> selectSysTenantPackageExport(SysTenantPackage sysTenantPackage) {
        return sysTenantPackageMapper.selectSysTenantPackageExport(sysTenantPackage);
    }
}
