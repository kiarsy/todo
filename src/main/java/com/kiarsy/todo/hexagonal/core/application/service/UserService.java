package com.kiarsy.todo.hexagonal.core.application.service;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.ITokenGenerator;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.IUserService;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.IUserRepository;
import com.kiarsy.todo.hexagonal.core.application.exception.LoginFailedException;
import com.kiarsy.todo.hexagonal.core.application.exception.UsernameNotUniqueException;

import java.util.HashMap;
import java.util.Map;

public class UserService implements IUserService {
    IUserRepository userRepository;
    ITokenGenerator jwtTokenUtil;

    public UserService(IUserRepository userRepository, ITokenGenerator jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Map<String, Object> login(String username, String password) {
        User user = userRepository.login(username, password);

        if (user == null) {
            throw new LoginFailedException();
        }

        String token = jwtTokenUtil.generateToken(user);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("name", user.getName());
        return result;
    }

    public Map<String, Object> createUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            if (e.getMessage().contains("UK_username")) {
                throw new UsernameNotUniqueException();
            }
        }
        return login(user.getUsername(),user.getPassword());
    }
}
