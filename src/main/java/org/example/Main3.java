package org.example;

import org.example.config.ConfigB;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.service.validator.UserValidator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main3 {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context =
                     new AnnotationConfigApplicationContext(ConfigB.class);) {
            UserService userService = context.getBean("userService", UserService.class);
            userService.findAll().forEach(System.out::println);

            UserValidator v = context.getBean(UserValidator.class);

//            UserRepository userRepository =
//                    context.getBean(UserRepository.class);
//            userRepository.findAll().forEach(System.out::println);
        }
    }
}
