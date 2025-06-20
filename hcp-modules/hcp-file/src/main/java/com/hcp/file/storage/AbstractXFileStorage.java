package com.hcp.file.storage;

import com.hcp.file.api.FileStorage;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.spring.file.MultipartFileWrapperAdapter;
import org.springframework.web.multipart.MultipartFile;

public class AbstractXFileStorage implements FileStorage {
    private FileStorageService storageService;

    public void init(FileStorageProperties properties) {
        this.storageService = FileStorageServiceBuilder.create(properties)
                .addFileWrapperAdapter(new MultipartFileWrapperAdapter())
                .useDefault()
                .build();
    }

    @Override
    public FileInfo upload(MultipartFile file) {
        return  storageService.of(file).upload();
    }
}
