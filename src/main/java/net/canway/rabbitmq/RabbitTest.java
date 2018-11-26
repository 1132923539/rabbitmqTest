package net.canway.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * @author eltons,  Date on 2018-11-12.
 */
@Service
public class RabbitTest {
    @Autowired
    RabbitTemplate rabbitTemplate;


    public void testSend(){
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println();
                System.out.println();
            }).start();

            new Hashtable<String, String>();
//            new ThreadPoolExecutor().submit()
            new HashSet<String>();
            rabbitTemplate.convertAndSend("exchange_test1","routing_test1",new HashMap<String,String>(20));
        }

    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "myTestRabbitMq",durable = "true"),
            exchange =@Exchange(value = "exchange_test1",durable = "true"),key = "routing_test1")})
    public void  testReceive(HashMap s){
        System.out.println(s);
    }
}


