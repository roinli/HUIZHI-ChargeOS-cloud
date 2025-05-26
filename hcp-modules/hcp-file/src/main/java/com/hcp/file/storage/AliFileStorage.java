package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class AliFileStorage extends AbstractXFileStorage {
    public AliFileStorage(SysOssConfig config) {
        FileStorageProperties.AliyunOssConfig aliyunOssConfig = new FileStorageProperties.AliyunOssConfig();
        aliyunOssConfig.setPlatform(config.getPlatformId());
        aliyunOssConfig.setDomain(config.getDomain());
        aliyunOssConfig.setAccessKey(config.getAccessKey());
        aliyunOssConfig.setSecretKey(config.getSecretKey());
        aliyunOssConfig.setBucketName(config.getBucketName());
        aliyunOssConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setAliyunOss(Collections.singletonList(aliyunOssConfig));
        init(properties);
    }
}
