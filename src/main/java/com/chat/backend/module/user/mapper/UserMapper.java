package com.chat.backend.module.user.mapper;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/16 17:55
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserDO findById(@Param("id") Long id);

}
