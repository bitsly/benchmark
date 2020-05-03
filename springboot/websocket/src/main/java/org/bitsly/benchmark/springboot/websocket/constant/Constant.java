package org.bitsly.benchmark.springboot.websocket.constant;

/**
 * 常量
 */
public final class Constant {
    //客户端访问的url部分，http://127.0.0.1:8080/endpoint
    public final static String ENDPOINT = "/endpoint";

    //客户端订阅的内容，全局广播，服务端有消息会推送给订阅此主题的客户端
    public final static String TOPIC_BROADCAST = "/topic/broadcast";

    //客户端订阅的内容，个人信息，服务端有消息会推送给订阅此主题的客户端
    public final static String TOPIC_REPLY = "/topic/reply";

    //客户端订阅的内容，个人通知，服务端有消息会推送给订阅此主题的客户端
    public final static String TOPIC_ENV = "/topic/env";

    //客户端向服务端告知的内容
    public final static String APP_SPEECH = "/app/speech";

}
