package com.kiarsy.todo.hexagonal.core.domain.ports.driver;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;

public interface ITokenGenerator {
    String generateToken(User user);
}
