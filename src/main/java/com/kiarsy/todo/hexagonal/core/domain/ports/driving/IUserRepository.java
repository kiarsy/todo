package com.kiarsy.todo.hexagonal.core.domain.ports.driving;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;

public interface IUserRepository {
    User login(String username, String password);
    User save(User entity);
}
