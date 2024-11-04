package com.itheima.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * @author daihongli
 * @version 1.0
 * @ClassName SpringRabbitListener
 * @Description: TODO
 * @Date 2024-11-01 12:45
 */
@Slf4j
@Component
public class SpringRabbitListener {
    // 利用RabbitListener来声明要监听的队列信息
    // 将来一旦监听的队列中有了消息，就会推送给当前服务，调用当前方法，处理消息。
    // 可以看到方法体中接收的就是消息体的内容
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        System.out.println("spring 消费者接收到消息：【" + msg + "】");
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "direct.queue1")
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者1接收到direct.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue2(String msg) {
        System.out.println("消费者2接收到direct.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg){
        System.out.println("消费者1接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg){
        System.out.println("消费者2接收到topic.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "topic.auto.queue1")
    public void listenTopicAutoQueue1(String msg){
        System.out.println("消费者1接收到topic.auto.queue1的消息：【" + msg + "】");
    }

    // 使用注解的方式监听队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenAnnDirectQueue1(String msg){
        System.out.println("消费者1接收到direct.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenAnnDirectQueue2(String msg){
        System.out.println("消费者2接收到direct.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "hmall.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenAnnTopicQueue1(String msg){
        System.out.println("消费者1接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "hmall.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenAnnTopicQueue2(String msg){
        System.out.println("消费者2接收到topic.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void listenSimpleQueueMessage(Map<String, Object> msg) throws InterruptedException {
        System.out.println("消费者接收到object.queue消息：【" + msg + "】");
    }

    @RabbitListener(queuesToDeclare = @Queue(
            name = "lazy.queue",
            durable = "true",
            arguments = @Argument(name = "x-queue-mode", value = "lazy")
    ))
    public void listenLazyQueue(String msg){
        log.info("接收到 lazy.queue的消息：{}", msg);
    }
}