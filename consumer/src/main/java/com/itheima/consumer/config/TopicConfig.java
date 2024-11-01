package com.itheima.consumer.config;

/**
 * @author daihongli
 * @version 1.0
 * @ClassName DirectConfig
 * @Description: TODO
 * @Date 2024-11-01 15:03
 */
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    /**
     * 声明交换机
     * @return Topic类型交换机
     */
    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange("hmall.auto.topic").build();
    }

    /**
     * 第1个队列
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue("topic.auto.queue1");
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue1WithChina(Queue topicQueue1, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("china.#");
    }

    /**
     * 第2个队列
     */
    @Bean
    public Queue topicQueue2(){
        return new Queue("topic.auto.queue2");
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue2WithNews(Queue topicQueue2, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("#.news");
    }
}
