package com.kiarsy.todo.hexagonal.infrastructure.presentation.controller;

import com.kiarsy.todo.hexagonal.core.application.service.TodoService;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.ITodoService;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration.JwtAuthentication;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo.Response;
import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/todo")
@Transactional
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ITodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Response list() {
        var lst = todoService.listAllTodosOfUser(JwtAuthentication.getAuthentication().getId());
        return new Response<>(lst);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Response create(@RequestBody Todo todo) {
        todoService.create(JwtAuthentication.getAuthentication().getId(), todo);
        return new Response();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable(value = "id") long id) {
        todoService.delete(JwtAuthentication.getAuthentication().getId(), id);
        return new Response();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Response update(@RequestBody Map<String, Object> body, @PathVariable(value = "id") long id) {
        Optional<Long> status = Optional.ofNullable(null);
        Optional<String> description = Optional.ofNullable(null);
        Optional<String> title = Optional.ofNullable(null);
        if (body.get("status") != null) {
            status = Optional.of(Long.valueOf(String.valueOf(body.get("status"))));
        }
        if (body.get("description") != null) {
            description = Optional.of(String.valueOf(body.get("description")));
        }
        if (body.get("title") != null) {
            title = Optional.of(String.valueOf(body.get("title")));
        }

        todoService.update(JwtAuthentication.getAuthentication().getId(), id, status, description, title);
        return new Response();
    }

}
