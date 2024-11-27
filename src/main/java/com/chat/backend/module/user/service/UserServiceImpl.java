package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId user id
     * @return {@link UserDO }
     * @author bunale
     */
    @Override
    public UserDO getByUserId(String userId) {
        return userMapper.selectOneByQuery(
                QueryWrapper.create()
                       .eq(UserDO::getUserId, userId)
        );
    }


    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDO }
     * @author liujie
     */
    @Override
    public UserDO getByUsername(String username) {
        return userMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq(UserDO::getName, username)
        );
    }



}
