package com.chat.backend.module.user.service.impl;

import com.chat.backend.module.user.entity.UserRoleDO;
import com.chat.backend.module.user.mapper.UserRoleMapper;
import com.chat.backend.module.user.service.UserRoleService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务层实现。
 *
 * @author 14110
 * @since 2024-11-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

}
