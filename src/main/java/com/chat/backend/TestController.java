package com.chat.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bunale
 * @since 2024/11/17
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
