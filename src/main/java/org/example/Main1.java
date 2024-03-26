package org.example;

import org.example.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:config.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.findAll().forEach(System.out::println);
    }
}