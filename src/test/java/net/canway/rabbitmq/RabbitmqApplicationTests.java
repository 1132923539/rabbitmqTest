package net.canway.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    @Autowired
    private RabbitTest rabbitTest;

    @Test
    public void contextLoads() {
        rabbitTest.testSend();
    }

    @Test
    public void receive() {
        String s = "";
        rabbitTest.testReceive(s);
    }

}
