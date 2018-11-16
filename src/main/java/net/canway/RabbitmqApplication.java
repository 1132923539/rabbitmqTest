package net.canway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqApplication {


    public static void main(String[] args) {
        System.out.println("success");


        SpringApplication.run(RabbitmqApplication.class, args);
    }


}
