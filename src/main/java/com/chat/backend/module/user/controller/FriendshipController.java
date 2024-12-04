package com.chat.backend.module.user.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.user.domain.param.*;
import com.chat.backend.module.user.domain.vo.UserVO;
import com.chat.backend.module.user.service.FriendshipService;
import com.chat.backend.util.UserContextHolder;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 好友关系数据 Controller。
 *
 * @author 14110
 * @since 2024-11-27
 */
@Tag(name = "好友关系数据 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     * 添加好友关系。
     *
     * @param param 参数对象
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @Operation(summary = "添加好友关系")
    @PostMapping("save")
    public R<?> save(@RequestBody AddFriendshipParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        friendshipService.addFriendship(param);
        return R.ok();
    }

    /**
     * 处理好友关系请求
     *
     * @param param 参数对象
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @Operation(summary = "处理好友关系请求")
    @PostMapping("handle")
    public R<?> handle(@RequestBody HandleFriendshipParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        friendshipService.handle(param);
        return R.ok();
    }

    /**
     * 根据主键批量删除。
     *
     * @param idsParam 参数对象
     * @return {@code true} 删除成功，{@code false} 删除失败@author bunale
     */
    @Operation(summary = "根据主键批量删除好友关系数据")
    @DeleteMapping("remove")
    public R<?> remove(IdsParam idsParam) {
        return R.ok(friendshipService.removeByIds(idsParam.getIds()));
    }

    /**
     * 分页查询当前用户好友申请
     *
     * @param param 分页参数
     * @return 分页对象
     */
    @Operation(summary = "分页查询当前用户好友申请")
    @GetMapping("/myFriendshipRequest")
    public R<?> myFriendshipRequest(GetMyFriendshipRequestParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        return R.ok(friendshipService.myFriendshipRequest(param));
    }

    /**
     * 分页查询当前用户的好友列表
     *
     * @param param 分页参数
     */
    @Operation(summary = "分页查询当前用户的好友列表")
    @GetMapping("/getMyFriendList")
    public R<?> getMyFriendList(GetMyFriendListParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        Page<UserVO> page = friendshipService.getMyFriendList(param);
        return R.ok(page);
    }

}
