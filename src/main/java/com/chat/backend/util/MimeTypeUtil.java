package com.chat.backend.util;

import java.util.HashMap;
import java.util.Map;

/**
 * MIME类型工具类
 *
 * @author bunale
 * @since 2024/12/1
 */
public class MimeTypeUtil {

    private static final Map<String, String> MIME_TYPE_TO_EXTENSION = new HashMap<>();

    static {
        MIME_TYPE_TO_EXTENSION.put("image/jpeg", ".jpg");
        MIME_TYPE_TO_EXTENSION.put("image/png", ".png");
        MIME_TYPE_TO_EXTENSION.put("image/gif", ".gif");
    }

    public static String getExtensionByMimeType(String mimeType) {
        return MIME_TYPE_TO_EXTENSION.getOrDefault(mimeType, "");
    }
}
