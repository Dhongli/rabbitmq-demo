package com.itheima.consumer.config;

/**
 * @author daihongli
 * @version 1.0
 * @ClassName MessageConfig
 * @Description: TODO
 * @Date 2024-11-01 15:55
 */

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    /*@Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }*/

    /*
     * @Description: 声明惰性队列, 数据直接存到磁盘
     * @date 2024-11-02 21:46
     **/
    @Bean
    public Queue lazyQueue() {
        return QueueBuilder
                .durable("lazy.queue")
                .lazy() // 开启Lazy模式
                .build();
    }
}