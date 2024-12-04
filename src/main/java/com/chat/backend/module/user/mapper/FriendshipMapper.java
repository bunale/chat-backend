package com.chat.backend.module.user.mapper;

import com.chat.backend.module.user.domain.entity.FriendshipDO;
import com.chat.backend.module.user.domain.vo.UserVO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 映射层。
 *
 * @author 14110
 * @since 2024-11-27
 */
public interface FriendshipMapper extends BaseMapper<FriendshipDO> {

    /**
     * 查询指定用户的好友列表
     *
     * @param username 好友名称
     * @param userId   目标用户id
     * @return {@link List }<{@link UserVO }>
     */
    List<UserVO> getFriendList(@Param("username") String username, @Param("userId") String userId);
}
