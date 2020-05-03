package org.bitsly.benchmark.springboot.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.bitsly.benchmark.springboot.rabbitmq.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Constant.QUEUE)
public class QueueConsumer {
    /**
     * 消费queue
     * @param msg
     */
    @RabbitHandler
    public void recieved(String msg) {
        log.info("{} recieved message: {}", Constant.QUEUE, msg);
    }
}
