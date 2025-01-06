package com.chat.backend.module.user.service;

import cn.hutool.core.collection.CollUtil;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.domain.vo.UserPageVO;
import com.chat.backend.module.user.mapper.UserMapper;
import com.chat.backend.util.PageUtils;
import com.github.pagehelper.PageHelper;
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
     * 根据用户id批量查询用户信息
     *
     * @param userIds 用户id集合
     * @return {@link List }<{@link UserDO }>
     */
    @Override
    public List<UserDO> getByUserIds(List<String> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return List.of();
        }

        return mapper.selectListByQuery(
                QueryWrapper.create()
                        .in(UserDO::getUserId, userIds)
        );
    }

    /**
     * 分页查询用户信息
     *
     * @param param 查询参数
     * @return {@link Page }<{@link UserDO }>
     */
    @Override
    public Page<UserPageVO> page(QueryUserDataParam param) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<UserPageVO> vos = userMapper.queryList(param);
        return PageUtils.of(page, vos);
    }

    private UserPageVO toUserPageVo(UserDO userDO) {
        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setUserId(userDO.getUserId());
        userPageVO.setName(userDO.getName());
        userPageVO.setEmail(userDO.getEmail());
        userPageVO.setAvatar(userDO.getAvatar());
        return userPageVO;
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
