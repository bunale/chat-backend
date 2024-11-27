package com.chat.backend.module.user.controller;

import com.chat.backend.module.user.entity.Friendship;
import com.chat.backend.module.user.service.FriendshipService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层。
 *
 * @author 14110
 * @since 2024-11-27
 */
@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    /**
     * 添加。
     *
     * @param friendship
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Friendship friendship) {
        return friendshipService.save(friendship);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return friendshipService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param friendship
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Friendship friendship) {
        return friendshipService.updateById(friendship);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Friendship> list() {
        return friendshipService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Friendship getInfo(@PathVariable Long id) {
        return friendshipService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Friendship> page(Page<Friendship> page) {
        return friendshipService.page(page);
    }

}
