package com.chat.backend.module.file;

import com.chat.backend.common.FileUploadSceneEnum;
import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import com.chat.backend.util.MimeTypeUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理服务实现类
 *
 * @author bunale
 * @since 2024/12/1
 */
@Service
@RequiredArgsConstructor
public class FileManagementServiceImpl implements FileManagementService {

    private final FileStorageService fileStorageService;

    /**
     * 上传头像
     *
     * @param bytes  图片字节数组
     * @param userId 用户id
     * @return {@link String }
     */
    @Override
    public String uploadAvatar(byte[] bytes, String userId) {
        String path = FileUploadSceneEnum.USER_AVATAR.getBasePath().formatted(userId);
        String avatarFilename = "avatar.png";
        FileInfo fileInfo = fileStorageService.of(bytes)
                .setPath(path)
                .setSaveFilename(avatarFilename)
                .upload();
        return "https://" + fileInfo.getUrl();
    }

    /**
     * 上传图片
     *
     * @param param 参数
     * @return {@link FileInfo }
     * @author bunale
     */
    @Override
    public FileInfo uploadImage(UploadFileParam param) {
        return fileStorageService.of(param.getFile())
                // 将图片大小调整到 1000*1000
                .image(img -> img.size(1000, 1000))
                // 再生成一张 200*200 的缩略图
                .thumbnail(th -> th.size(200, 200))
                .upload();
    }

    /**
     * 上传文件
     *
     * @param param file
     * @return {@link Object }
     * @author bunale
     */
    @Override
    public FileInfo upload(UploadFileParam param) {
        FileUploadSceneEnum sceneEnum = FileUploadSceneEnum.getByName(param.getScene());
        MultipartFile file = param.getFile();
        String contentType = file.getContentType();

        FileInfo result;
        result = switch (sceneEnum) {
            case USER_AVATAR -> {
                String path = sceneEnum.getBasePath().formatted(param.getCurrentUser().getUserId());
                // 根据contentType判断上传的图片类型
                if (contentType == null || !contentType.startsWith("image/")) {
                    throw new BaseException(ErrorCode.AVATAR_FORMAT_ERROR);
                }
                String avatarFilename = "avatar%s".formatted(MimeTypeUtil.getExtensionByMimeType(contentType));
                yield fileStorageService.of(file).setPath(path).setSaveFilename(avatarFilename).upload();
            }
            case CONVERSATION_MESSAGE -> {
                String path = sceneEnum.getBasePath().formatted(param.getConversationId());
                yield fileStorageService.of(file).setPath(path).upload();
            }
            default -> fileStorageService.of(file).setPath(FileUploadSceneEnum.ROOT.getBasePath()).upload();
        };

        return result;
    }

}
