package com.chat.backend.module.message.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.message.domain.param.AddConversationParam;
import com.chat.backend.module.message.domain.vo.ConversationVO;
import com.chat.backend.module.message.service.MessageService;
import com.chat.backend.util.UserContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final MessageService messageService;

    @Operation(summary = "新增会话")
    @PostMapping("/add")
    public R<?> add(@RequestBody @Valid AddConversationParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        ConversationVO vo = messageService.add(param);
        return R.ok(vo);
    }


}
