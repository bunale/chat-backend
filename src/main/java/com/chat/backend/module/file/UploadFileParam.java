package com.chat.backend.module.file;

import com.chat.backend.common.UserContext;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件参数实体类
 *
 * @author bunale
 * @since 2024/12/1
 */
@Data
public class UploadFileParam {

    @NotBlank
    private String scene;

    @NotNull
    private MultipartFile file;

    private Long conversationId;

    @Hidden
    private UserContext currentUser;
}
