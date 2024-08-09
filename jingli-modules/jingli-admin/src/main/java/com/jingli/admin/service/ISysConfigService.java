package com.jingli.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.admin.model.SysConfig;


/**
 * 参数配置 服务层
 *
 * @author zyjc
 */
public interface ISysConfigService extends IService<SysConfig> {

    /**
     * 根据键名查询参数配置信息
     */
    String selectConfigByKey(String configKey);
}
