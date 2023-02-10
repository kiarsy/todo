package com.kiarsy.todo.hexagonal.infrastructure.presentation.controller;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import com.kiarsy.todo.hexagonal.core.application.service.UserService;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo.Response;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{login}", method = RequestMethod.POST)
    public Response login(@RequestBody Map<String, String> body) {
        var tokenObject = this.userService.login(body.get("username"), body.get("password"));

        return new Response<>(tokenObject);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Response register(@RequestBody @Valid User user) {
        var tokenObject = this.userService.createUser(user);
        return new Response<>(tokenObject);
    }
}
