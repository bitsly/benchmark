package org.bitsly.benchmark.springboot.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.bitsly.benchmark.springboot.websocket.constant.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * websocket配置
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //订阅点.withSockJS()
        registry.addEndpoint(Constant.ENDPOINT).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //设置订阅通道(客户端可订阅)
//        config.enableSimpleBroker("/topic");
        //接收APP(客户端)消息的路由前缀，可通过@MessageMapping 映射到方法
//        config.setApplicationDestinationPrefixes("/app");
    }

}
