package com.chat.backend.module.message.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.param.GetConversationParam;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.chat.backend.module.message.service.ConversationService;
import com.chat.backend.util.UserContextHolder;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 消息 Controller
 *
 * @author liujie
 * @since 2024/12/4
 */
@Tag(name = "消息 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;

    @Operation(summary = "开始会话")
    @PostMapping("/start")
    public R<ConversationVO> start(@RequestBody @Valid AddConversationParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        ConversationVO vo = conversationService.start(param);
        return R.ok(vo);
    }

    @Operation(summary = "根据会话ID获取会话信息")
    @GetMapping("/getById")
    public R<ConversationVO> getById(@RequestParam Long conversationId) {
        ConversationVO vo = conversationService.getById(conversationId);
        return R.ok(vo);
    }

    @Operation(summary = "分页查询用户会话列表")
    @GetMapping("/page")
    public R<Page<ConversationVO>> page(GetConversationParam pageParam) {
        pageParam.setCurrentUser(UserContextHolder.getCurrentUserContext());
        Page<ConversationVO> page = conversationService.getConversationPage(pageParam);
        return R.ok(page);
    }

}
