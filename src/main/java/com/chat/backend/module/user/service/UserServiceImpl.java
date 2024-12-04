package com.chat.backend.module.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.domain.vo.UserVO;
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
    public Page<UserVO> page(QueryUserDataParam param) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (StrUtil.isNotBlank(param.getUsername())) {
            queryWrapper.like(UserDO::getName, "%" + param.getUsername() + "%");
        }

        Page<UserDO> page = page(Page.of(param.getPageNum(), param.getPageSize()), queryWrapper);
        List<UserVO> userVOList = page.getRecords().stream().map(this::toUserVo).toList();
        return PageUtils.of(page, userVOList);
    }

    private UserVO toUserVo(UserDO userDO) {
        UserVO userVO = new UserVO();
        userVO.setUserId(userDO.getUserId());
        userVO.setName(userDO.getName());
        userVO.setEmail(userDO.getEmail());
        userVO.setAvatar(userDO.getAvatar());
        userVO.setStatus(userDO.getStatus());
        userVO.setCreateUserId(userDO.getCreateUserId());
        userVO.setCreateTime(userDO.getCreateTime());
        userVO.setLastUpdateUserId(userDO.getLastUpdateUserId());
        userVO.setLastUpdateTime(userDO.getLastUpdateTime());
        return userVO;
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
