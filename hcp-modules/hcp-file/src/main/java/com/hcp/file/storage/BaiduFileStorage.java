package com.hcp.file.storage;

import com.hcp.file.domain.SysOssConfig;
import org.dromara.x.file.storage.core.FileStorageProperties;

import java.util.Collections;

public class BaiduFileStorage extends AbstractXFileStorage {
    public BaiduFileStorage(SysOssConfig config) {
        FileStorageProperties.BaiduBosConfig baiduBosConfig = new FileStorageProperties.BaiduBosConfig();
        baiduBosConfig.setPlatform(config.getPlatformId());
        baiduBosConfig.setDomain(config.getDomain());
        baiduBosConfig.setAccessKey(config.getAccessKey());
        baiduBosConfig.setSecretKey(config.getSecretKey());
        baiduBosConfig.setBucketName(config.getBucketName());
        baiduBosConfig.setBasePath(config.getBasePath());
        FileStorageProperties properties = new FileStorageProperties();
        properties.setDefaultPlatform(config.getPlatformId());
        properties.setBaiduBos(Collections.singletonList(baiduBosConfig));
        init(properties);
    }
}
