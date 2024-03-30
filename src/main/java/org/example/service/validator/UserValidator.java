package org.example.service.validator;

import org.example.entity.User;

@Validator
public class UserValidator {

    public void validateUser(User user) {
        if (user == null || user.getFirstname() == null) {
            throw new RuntimeException();//- use custom exception and add message
        }
    }
}
