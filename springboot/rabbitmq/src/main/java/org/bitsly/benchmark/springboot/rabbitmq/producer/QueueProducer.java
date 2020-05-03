package org.bitsly.benchmark.springboot.rabbitmq.producer;

import org.bitsly.benchmark.springboot.rabbitmq.constant.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QueueProducer {
    @Resource
    private AmqpTemplate rabbitTemplate;

    /**
     * 向queue发送
     * @param msg
     */
    public void send(String msg) {
        rabbitTemplate.convertAndSend(Constant.QUEUE, msg);
    }
}
