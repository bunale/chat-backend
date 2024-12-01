package com.chat.backend.module.file;

import com.chat.backend.common.R;
import com.chat.backend.util.UserContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件存储 Controller
 *
 * @author bunale
 * @since 2024/12/1
 */
@Tag(name = "文件存储 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/file/management")
public class FileManagementController {


    private final FileManagementService fileManagementService;

    /**
     * 上传文件
     */
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public R<?> upload(UploadFileParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        return R.ok(fileManagementService.upload(param));
    }

    /**
     * 上传图片，成功返回文件信息
     * 图片处理使用的是 <a href="https://github.com/coobird/thumbnailator">thumbnailator</a>
     */
    @Operation(summary = "上传图片")
    @PostMapping("/upload-image")
    public FileInfo uploadImage(UploadFileParam param) {
        return fileManagementService.uploadImage(param);
    }

}
