package org.bitsly.benchmark.springboot.websocket.controller;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bitsly.benchmark.springboot.websocket.service.MessageService;
import org.bitsly.benchmark.springboot.websocket.constant.Constant;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订阅
 */
@RestController
@Slf4j
public class MessageController {
    @Resource
    private MessageService messageService;

    /**
     * 初次订阅时，获取一些环境
     * @return
     */
    @SubscribeMapping(Constant.TOPIC_ENV)
    public Long env() {
        return messageService.getVersion();
    }

    /**
     * 接收客户端发来的信息，并通过指定主题返还给客户端
     * @param message
     * @return
     */
    @MessageMapping(Constant.APP_SPEECH)
    @SendTo(Constant.TOPIC_REPLY)
    public String reply(String message) {
        return message;
    }
}
