package com.kiarsy.todo.hexagonal.core.domain.ports.driver;
import com.kiarsy.todo.hexagonal.core.domain.entities.User;

import java.util.Map;

public interface IUserService {
    public Map<String, Object> login(String username, String password);

    public Map<String, Object> createUser(User user);
}
