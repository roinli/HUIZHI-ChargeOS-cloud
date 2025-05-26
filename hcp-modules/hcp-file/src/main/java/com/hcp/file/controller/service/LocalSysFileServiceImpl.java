package com.hcp.file.controller.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.hcp.common.core.context.SecurityContextHolder;
import com.hcp.file.api.FileStorage;
import com.hcp.file.constant.PlatformConstant;
import com.hcp.file.domain.SysOssConfig;
import com.hcp.file.service.ISysOssConfigService;
import com.hcp.file.storage.*;
import com.hcp.file.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 本地文件存储
 *
 * @author vctgo
 */
@Slf4j
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService
{
    @Autowired
    private ISysOssConfigService ossConfigService;
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        Long tenantId = SecurityContextHolder.getTenantId();
        log.info("租户ID：{}", tenantId);
        SysOssConfig ossConfig = ossConfigService.selectDefaultOssConfig(tenantId);
        if (ObjectUtil.isNull(ossConfig)){
            throw new Exception("当前租住未配置默认存储配置");
        }
        FileStorage storage = null;
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.KODO)){
            storage = new QiniuFileStorage(ossConfig);
        }
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.MINIO)){
            storage = new MinioStorage(ossConfig);
        }
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.OBS)){
            storage = new HuaweiFileStorage(ossConfig);
        }
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.COS)){
            storage = new TencentFileStorage(ossConfig);
        }
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.OSS)){
            storage = new AliFileStorage(ossConfig);
        }
        if (StringUtils.equals(ossConfig.getPlatformId(), PlatformConstant.BOS)){
            storage = new BaiduFileStorage(ossConfig);
        }
        assert storage != null;
        FileInfo upload = storage.upload(file);
        log.info("上传结果:{}", JSONObject.toJSONString(upload));
        return upload.getUrl();
    }
}
