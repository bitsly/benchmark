package org.bitsly.benchmark.springboot.rabbitmq.config;

import org.bitsly.benchmark.springboot.rabbitmq.constant.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue benchmark() {
        return new Queue(Constant.QUEUE);
    }
}
