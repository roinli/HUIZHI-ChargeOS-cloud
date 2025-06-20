package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class HuaweiFileStorage extends AbstractXFileStorage {
    public HuaweiFileStorage(SysOssConfig config) {
        FileStorageProperties.HuaweiObsConfig huaweiObsConfig = new FileStorageProperties.HuaweiObsConfig();
        huaweiObsConfig.setPlatform(config.getPlatformId());
        huaweiObsConfig.setDomain(config.getDomain());
        huaweiObsConfig.setAccessKey(config.getAccessKey());
        huaweiObsConfig.setSecretKey(config.getSecretKey());
        huaweiObsConfig.setBucketName(config.getBucketName());
        huaweiObsConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setHuaweiObs(Collections.singletonList(huaweiObsConfig));
        init(properties);
    }
}
