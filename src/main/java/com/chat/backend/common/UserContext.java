package com.chat.backend.common;

import lombok.Data;

import java.util.List;

/**
 * @author bunale
 * @since 2024/11/27
 */
@Data
public class UserContext {

    private String userId;
    private String username;
    private String email;
    private String avatar;
    private List<String> role;

}
