package com.hcp.file.api;

import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
    FileInfo upload(MultipartFile file);
}
