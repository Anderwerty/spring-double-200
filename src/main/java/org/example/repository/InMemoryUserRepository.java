package org.example.repository;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class InMemoryUserRepository implements UserRepository{
    @Override
    public List<User> findAll() {
        User user = new User();
        user.setFirstname("Taras");
        user.setLastname("Shevchenko");

        return Collections.singletonList(user);
    }
}
