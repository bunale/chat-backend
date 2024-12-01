package com.chat.backend.util;

import com.chat.backend.common.UserContext;
import com.chat.backend.exception.BaseException;
import com.chat.backend.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author bunale
 * @since 2024/11/27
 */
public class UserContextHolder {

    /**
     * 获取当前生效的用户上下文中的用户id
     *
     * @return {@link String }
     * @author bunale
     */
    public static String getCurrentUserId() {
        UserContext userContext = getCurrentUserContext();
        return userContext.getUserId();
    }

    /**
     * 获取当前生效的用户上下文
     * @return {@link UserContext }
     */
    public static UserContext getCurrentUserContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BaseException(ErrorCode.AUTHENTICATION_EXPIRE_ERROR);
        }

        return (UserContext) authentication.getPrincipal();
    }
}
