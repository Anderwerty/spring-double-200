package org.example.config;

import org.example.repository.HibernateUserRepository;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@Import(ConfigB.class)
//@ImportResource
public class ConfigA {

    @Bean("userService")
    public UserService userService123(
            @Qualifier("hibernateUserRepository")
            UserRepository userRepository) {
        //return UserService.builder().with...(). build();
        return new UserService(userRepository, null);
    }


    @Bean
    public UserRepository inMemoryUserRepository(){
        return new InMemoryUserRepository();
    }

    @Bean
    public UserRepository hibernateUserRepository(){
        return new HibernateUserRepository();
    }
}
