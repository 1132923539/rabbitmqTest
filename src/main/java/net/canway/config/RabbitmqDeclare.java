package net.canway.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eltons,  Date on 2019-01-06.
 * 这个类是用来声明交换机与队列的，并将相应的队列绑定到交换机，也可以用注解形式实现，注解形式更轻松友好，建议注解形式
 * @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
 * bindings = {
 * @QueueBinding( key = "routing_test2",//路由key
 * value = @Queue(value = "queue_notify_rtx", durable = "true"),
 * exchange = @Exchange(value = "exchange_test2", durable = "true", /*delayed = "true",
 * type="topic"))})
 */

@Configuration
public class RabbitmqDeclare {
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_TOPIC_INFORM = "exchange_topic_inform";
    public static final String ROUTINGKEY_EMAIL = "inform.#.email.#";
    public static final String ROUTINGKEY_SMS = "inform.#.sms.#";

    @Bean(EXCHANGE_TOPIC_INFORM)
    public Exchange getTopicInformExchange() {
        // durable表示rabbitmq重启后交换机任存在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_INFORM).durable(true).build();
    }

    @Bean(QUEUE_INFORM_EMAIL)
    public Queue getEmailQueue() {
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    @Bean(QUEUE_INFORM_SMS)
    public Queue getSmsQueue() {
        return new Queue(QUEUE_INFORM_SMS);
    }

    //队列绑定交换机
    @Bean
    public Binding getEmailBinding(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                   @Qualifier(EXCHANGE_TOPIC_INFORM) Exchange exchange
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }

    //队列绑定交换机
    @Bean
    public Binding getSmsBinding(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                 @Qualifier(EXCHANGE_TOPIC_INFORM) Exchange exchange
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
    }
}
