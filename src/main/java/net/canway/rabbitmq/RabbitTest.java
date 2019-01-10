package net.canway.rabbitmq;

import net.canway.config.RabbitmqDeclare;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

/**
 * @author eltons,  Date on 2018-11-12.
 */
@Service
public class RabbitTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitmqDeclare.EXCHANGE_TOPIC_INFORM, RabbitmqDeclare.QUEUE_INFORM_EMAIL, "aaaaaaaaaaaaa", message -> {
                message.getMessageProperties().setHeader("x-delay", 3000);
                return message;
            });

        }
    }

    public void testSend2() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange_test1", "routing_test1", "aaaaaaaaaaaaa");
        }
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "myTestRabbitMq",durable = "true"),
            exchange =@Exchange(value = "exchange_test1",durable = "true"),key = "routing_test1")})
    public void testReceive(String s) {
        System.out.println(s);
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = {
                    @QueueBinding(
                            key = "rtx",
                            value = @Queue(value = "queue_notify_rtx", durable = "true"),
                            exchange = @Exchange(value = "exchange_notify", durable = "true", /*delayed = "true",*/ type = "topic"))})
    public void testReceive2(String s) {
        System.out.println(s);

    }

    //若配置文件中已经配置了相应的交换机，队列以及队列绑定到交换机，则这里可以直接监听交换机
    @RabbitListener(queues = {RabbitmqDeclare.QUEUE_INFORM_EMAIL})
    public void testReceive3(String s, Message message) {
        System.out.println(s);

    }
}


