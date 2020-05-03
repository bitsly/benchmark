package org.bitsly.benchmark.springboot.websocket.service;

import javax.annotation.Resource;
import org.bitsly.benchmark.springboot.websocket.constant.Constant;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 消息服务
 */
@Service
public class MessageService {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    private Long version = 1L;
    private Long versionOld = version;

    public Long getVersion() {
        version = version + 1;
        return version;
    }

    /**
     * 定时向客户端推送环境更新
     */
    @Scheduled(fixedRate = 1000)
    public void env() {
        if (versionOld.equals(version)) {
            return;
        }
        versionOld = version;
        simpMessagingTemplate.convertAndSend(Constant.TOPIC_ENV, version);
    }

    /**
     * 定时向客户端广播当前时间
     */
    @Scheduled(fixedRate = 10000)
    public void cronPushMessageToClient() {
        simpMessagingTemplate.convertAndSend(Constant.TOPIC_BROADCAST, System.currentTimeMillis());
    }

}
