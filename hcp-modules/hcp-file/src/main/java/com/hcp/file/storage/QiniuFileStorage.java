package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class QiniuFileStorage extends AbstractXFileStorage {
    public QiniuFileStorage(SysOssConfig config) {
        FileStorageProperties.QiniuKodoConfig qiniuKodoConfig = new FileStorageProperties.QiniuKodoConfig();
        qiniuKodoConfig.setPlatform(config.getPlatformId());
        qiniuKodoConfig.setDomain(config.getDomain());
        qiniuKodoConfig.setAccessKey(config.getAccessKey());
        qiniuKodoConfig.setSecretKey(config.getSecretKey());
        qiniuKodoConfig.setBucketName(config.getBucketName());
        qiniuKodoConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setQiniuKodo(Collections.singletonList(qiniuKodoConfig));
        init(properties);
    }
}
