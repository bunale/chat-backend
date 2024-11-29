package com.chat.backend.module.user.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类。
 *
 * @author 14110
 * @since 2024-11-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_user_role")
public class UserRoleDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String userId;

    private String roleKey;

    private LocalDateTime createdTime;

}
