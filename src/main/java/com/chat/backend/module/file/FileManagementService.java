package com.chat.backend.module.file;

import org.dromara.x.file.storage.core.FileInfo;

/**
 * 文件管理服务接口
 * @author bunale
 * @since 2024/12/1
 */

public interface FileManagementService {
    /**
     * 上传文件
     *
     * @param param 参数
     * @return {@link FileInfo }
     * @author bunale
     */
    FileInfo upload(UploadFileParam param);

    /**
     * 上传图片
     *
     * @param param 参数
     * @return {@link FileInfo }
     * @author bunale
     */
    FileInfo uploadImage(UploadFileParam param);
}
