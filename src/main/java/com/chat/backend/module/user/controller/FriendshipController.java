package com.chat.backend.module.user.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.user.domain.entity.FriendshipDO;
import com.chat.backend.module.user.domain.param.IdsParam;
import com.chat.backend.module.user.service.FriendshipService;
import com.mybatisflex.core.paginate.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 好友关系数据API。
 *
 * @author 14110
 * @since 2024-11-27
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     * 添加。
     *
     * @param friendshipDO 参数对象
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public R<?> save(@RequestBody FriendshipDO friendshipDO) {
        return R.ok(friendshipService.save(friendshipDO));
    }

    /**
     * 根据主键批量删除。
     *
     * @param idsParam 参数对象
     * @return {@code true} 删除成功，{@code false} 删除失败@author bunale
     */
    @DeleteMapping("remove")
    public R<?> remove(IdsParam idsParam) {
        return R.ok(friendshipService.removeByIds(idsParam.getIds()));
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public R<?> page(Page<FriendshipDO> page) {
        return R.ok(friendshipService.page(page));
    }

}
