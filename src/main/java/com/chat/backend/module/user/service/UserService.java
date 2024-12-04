package com.chat.backend.module.user.service;

import com.chat.backend.module.user.domain.entity.UserDO;
import com.chat.backend.module.user.domain.param.QueryUserDataParam;
import com.chat.backend.module.user.domain.vo.UserVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * @author ：bunale
 * @since ：Created in 2024/11/17 16:03
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDO }
     * @author liujie
     */
    UserDO getByUsername(String username);


    /**
     * 根据用户id获取用户信息
     *
     * @param userId user id
     * @return {@link UserDO }
     * @author bunale
     */
    UserDO getByUserId(String userId);

    /**
     * 分页查询用户信息
     *
     * @param param 查询参数
     * @return {@link Page }<{@link UserDO }>
     */
    Page<UserVO> page(QueryUserDataParam param);

    /**
     * 根据用户id批量查询用户信息
     *
     * @param userIds 用户id集合
     * @return {@link List }<{@link UserDO }>
     */
    List<UserDO> getByUserIds(List<String> userIds);
}
