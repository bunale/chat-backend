package com.chat.backend.module.user.service.impl;

import com.chat.backend.module.user.domain.entity.RoleDO;
import com.chat.backend.module.user.mapper.RoleMapper;
import com.chat.backend.module.user.service.RoleService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务层实现。
 *
 * @author 14110
 * @since 2024-11-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

}
