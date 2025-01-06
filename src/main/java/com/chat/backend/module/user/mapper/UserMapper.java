package com.chat.backend.module.user.mapper;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.domain.vo.UserPageVO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/16 17:55
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserDO findById(@Param("id") Long id);

    /**
     * 条件查询用户列表
     *
     * @param param param
     * @return {@link List }<{@link UserDO }>
     * @author bunale
     */
    List<UserPageVO> queryList(QueryUserDataParam param);
}
