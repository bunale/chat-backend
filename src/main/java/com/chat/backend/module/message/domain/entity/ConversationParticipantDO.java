package com.chat.backend.module.message.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会话参与者表 实体类。
 *
 * @author 14110
 * @since 2024-12-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_conversation_participant")
public class ConversationParticipantDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话ID
     */
    @Id
    private Long conversationId;

    /**
     * 用户ID
     */
    @Id
    private String userId;

    /**
     * 在会话中的角色，1：管理员，2：创建人，3：普通参与者
     */
    private String conversationRole;

    /**
     * 加入时间
     */
    private LocalDateTime joinedAt;

}
