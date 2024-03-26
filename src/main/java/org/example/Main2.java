package org.example;

import org.example.config.ConfigA;
import org.example.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigA.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.findAll().forEach(System.out::println);

        context.close();
    }
}
