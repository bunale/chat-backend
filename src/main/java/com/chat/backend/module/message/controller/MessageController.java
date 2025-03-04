package com.chat.backend.module.message.controller;

import com.chat.backend.common.R;
import com.chat.backend.module.message.domain.param.GetMessageParam;
import com.chat.backend.module.message.domain.param.SendMessageParam;
import com.chat.backend.module.message.domain.vo.MessageVO;
import com.chat.backend.module.message.service.MessageService;
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
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "发送消息到指定的会话中")
    @PostMapping("/send")
    public R<?> send(@RequestBody @Valid SendMessageParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        messageService.send(param);
        return R.ok();
    }

    @Operation(summary = "分页查询消息")
    @GetMapping("/page")
    public R<Page<MessageVO>> page(GetMessageParam param) {
        param.setCurrentUser(UserContextHolder.getCurrentUserContext());
        Page<MessageVO> page = messageService.page(param);
        return R.ok(page);
    }


}
