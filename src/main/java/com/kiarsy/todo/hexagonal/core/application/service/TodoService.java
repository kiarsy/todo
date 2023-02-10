package com.kiarsy.todo.hexagonal.core.application.service;

import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.ITodoService;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.ITodoRepository;
import com.kiarsy.todo.hexagonal.core.application.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TodoService implements ITodoService {
    ITodoRepository todoRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public TodoService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listAllTodosOfUser(long owner_id) {
        return todoRepository.findAllByOwnerId(owner_id);
    }

    public void create(long owner_id, Todo todo) {
        todo.createNewTodo(owner_id);
        todoRepository.save(todo);
    }

    @Override
    public void delete(long owner_id, long id) {
        todoRepository.deleteOne(owner_id,id);
    }

    public void update(long owner_id, long id, Optional<Long> status, Optional<String> description, Optional<String> title) {
        var todoEntity = todoRepository.findOneByOwnerIdAndId(owner_id, id);
        if (todoEntity.isEmpty()) {
            throw new ResourceNotFoundException("Todo not found");
        }
        var todo = todoEntity.get();
        todo.editTodo(status,description,title);

        todoRepository.save(todo);
    }
}
