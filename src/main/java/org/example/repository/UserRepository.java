package org.example.repository;

import org.example.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
}
