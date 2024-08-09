package com.jingli.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingli.admin.mapper.SysConfigMapper;
import com.jingli.admin.model.SysConfig;
import com.jingli.admin.service.ISysConfigService;
import com.jingli.admin.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 参数配置 服务层实现
 *
 * @author zyjc
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {


    @Override
    public String selectConfigByKey(String configKey) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysConfig::getConfigKey, configKey);
        SysConfig sysConfig = this.getOne(queryWrapper);

        if (ObjUtil.isNotEmpty(sysConfig)) {
            return sysConfig.getConfigValue();
        }

        return StringUtils.EMPTY;
    }
}
