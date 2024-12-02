package com.chat.backend.module.user.service;

import cn.hutool.core.util.StrUtil;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.domain.resp.UserResp;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.PageUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;

    /**
     * 分页查询用户信息
     *
     * @param param 查询参数
     * @return {@link Page }<{@link UserDO }>
     */
    @Override
    public Page<UserResp> page(QueryUserDataParam param) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (StrUtil.isNotBlank(param.getUsername())) {
            queryWrapper.eq(UserDO::getName, param.getUsername());
        }

        Page<UserDO> page = page(Page.of(param.getPageNum(), param.getPageSize()), queryWrapper);
        List<UserResp> userRespList = page.getRecords().stream().map(this::toUserResp).toList();
        return PageUtils.of(page, userRespList);
    }

    private UserResp toUserResp(UserDO userDO) {
        UserResp userResp = new UserResp();
        userResp.setUserId(userDO.getUserId());
        userResp.setName(userDO.getName());
        userResp.setEmail(userDO.getEmail());
        userResp.setAvatar(userDO.getAvatar());
        userResp.setStatus(userDO.getStatus());
        userResp.setCreateUser_id(userDO.getCreateUser_id());
        userResp.setCreateTime(userDO.getCreateTime());
        userResp.setLastUpdateUserId(userDO.getLastUpdateUserId());
        userResp.setLastUpdateTime(userDO.getLastUpdateTime());
        return userResp;
    }

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
