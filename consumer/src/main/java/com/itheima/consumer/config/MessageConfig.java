package com.itheima.consumer.config;

/**
 * @author daihongli
 * @version 1.0
 * @ClassName MessageConfig
 * @Description: TODO
 * @Date 2024-11-01 15:55
 */
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }
}