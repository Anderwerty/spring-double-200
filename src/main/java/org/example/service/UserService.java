package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// @Service
public class UserService implements InitializingBean, DisposableBean {

    private final UserRepository userRepository;

    public UserService(@Qualifier("hibernateUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService >>");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy........");
    }
}
