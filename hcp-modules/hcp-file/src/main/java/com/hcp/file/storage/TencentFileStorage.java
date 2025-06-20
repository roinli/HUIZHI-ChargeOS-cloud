package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class TencentFileStorage extends AbstractXFileStorage {
    public TencentFileStorage(SysOssConfig config) {
        FileStorageProperties.TencentCosConfig tencentCosConfig = new FileStorageProperties.TencentCosConfig();
        tencentCosConfig.setPlatform(config.getPlatformId());
        tencentCosConfig.setDomain(config.getDomain());
        tencentCosConfig.setSecretId(config.getAccessKey());
        tencentCosConfig.setSecretKey(config.getSecretKey());
        tencentCosConfig.setBucketName(config.getBucketName());
        tencentCosConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setTencentCos(Collections.singletonList(tencentCosConfig));
        init(properties);
    }
}
