package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.FriendshipDO;
import com.chat.backend.module.user.mapper.FriendshipMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bunale
 * @since 2024/11/27
 */
@Service
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, FriendshipDO> implements FriendshipService {

}
