package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.FriendshipDO;
import com.chat.backend.module.user.domain.entity.FriendshipStatus;
import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.AddFriendshipParam;
import com.chat.backend.module.user.domain.param.GetMyFriendListParam;
import com.chat.backend.module.user.domain.param.GetMyFriendshipRequestParam;
import com.chat.backend.module.user.domain.param.HandleFriendshipParam;
import com.chat.backend.module.user.domain.vo.FriendshipRequestVO;
import com.chat.backend.module.user.domain.vo.UserVO;
import com.chat.backend.module.user.mapper.FriendshipMapper;
import com.chat.backend.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bunale
 * @since 2024/11/27
 */
@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, FriendshipDO> implements FriendshipService {

    private final UserService userService;

    /**
     * 分页查询当前用户的好友列表
     *
     * @param param 参数
     * @return {@link Page }<{@link UserVO }>
     */
    @Override
    public Page<UserVO> getMyFriendList(GetMyFriendListParam param) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<UserVO> friends = mapper.getFriendList(param.getUsername(), param.getCurrentUser().getUserId());
        return PageUtils.of(page, friends);
    }

    /**
     * 分页查询好友关系数据
     *
     * @param param 参数
     * @return {@link Page }<{@link FriendshipDO }>
     */
    @Override
    public Page<FriendshipRequestVO> myFriendshipRequest(GetMyFriendshipRequestParam param) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (param.getStatus() != null) {
            queryWrapper.eq(FriendshipDO::getStatus, param.getStatus());
        }
        queryWrapper.eq(FriendshipDO::getReceiver, param.getCurrentUser().getUserId());
        queryWrapper.orderBy(FriendshipDO::getCreatedTime).desc();
        Page<FriendshipDO> page = page(Page.of(param.getPageNum(), param.getPageSize()), queryWrapper);

        List<FriendshipRequestVO> vos = page.getRecords().stream().map(this::toFriendshipRequestVo).toList();
        return PageUtils.of(page, vos);
    }

    private FriendshipRequestVO toFriendshipRequestVo(FriendshipDO friendshipDO) {
        UserDO initiator = userService.getByUserId(friendshipDO.getInitiator());

        FriendshipRequestVO friendshipRequestVO = new FriendshipRequestVO();
        friendshipRequestVO.setId(friendshipDO.getId());
        friendshipRequestVO.setInitiator(friendshipDO.getInitiator());
        friendshipRequestVO.setInitiatorUsername(initiator.getName());
        friendshipRequestVO.setInitiatorAvatar(initiator.getAvatar());
        friendshipRequestVO.setReceiver(friendshipDO.getReceiver());
        friendshipRequestVO.setStatus(friendshipDO.getStatus());
        friendshipRequestVO.setRemark(friendshipDO.getRemark());
        friendshipRequestVO.setCreatedTime(friendshipDO.getCreatedTime());
        friendshipRequestVO.setUpdatedTime(friendshipDO.getUpdatedTime());
        return friendshipRequestVO;
    }

    /**
     * 处理好友关系请求
     *
     * @param param 参数
     */
    @Override
    public void handle(HandleFriendshipParam param) {
        FriendshipDO friendshipDO = new FriendshipDO();
        friendshipDO.setId(param.getFriendshipId());
        friendshipDO.setStatus(param.getStatus());
        friendshipDO.setUpdatedTime(LocalDateTime.now());
        mapper.update(friendshipDO);
    }

    /**
     * 添加好友关系数据
     *
     * @param param 参数
     */
    @Override
    public void addFriendship(AddFriendshipParam param) {
        FriendshipDO friendshipDO = new FriendshipDO();
        friendshipDO.setInitiator(param.getCurrentUser().getUserId());
        friendshipDO.setReceiver(param.getReceiver());
        friendshipDO.setStatus(FriendshipStatus.PENDING.getCode());
        friendshipDO.setRemark(param.getRemark());
        friendshipDO.setCreatedTime(LocalDateTime.now());
        mapper.insert(friendshipDO);
    }
}
