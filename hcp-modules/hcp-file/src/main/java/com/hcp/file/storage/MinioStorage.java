package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class MinioStorage extends AbstractXFileStorage {
    public MinioStorage(SysOssConfig config) {
        FileStorageProperties.MinioConfig minioConfig = new FileStorageProperties.MinioConfig();
        minioConfig.setPlatform(config.getPlatformId());
        minioConfig.setDomain(config.getDomain());
        minioConfig.setAccessKey(config.getAccessKey());
        minioConfig.setSecretKey(config.getSecretKey());
        minioConfig.setBucketName(config.getBucketName());
        minioConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setMinio(Collections.singletonList(minioConfig));
        init(properties);
    }
}
