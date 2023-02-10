package com.kiarsy.todo.hexagonal.core.domain.ports.driver;
import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoService {
    public List<Todo> listAllTodosOfUser(long owner_id);

    public void create(long owner_id, Todo todo);

    public void delete(long owner_id,long id);

    public void update(long owner_id, long id, Optional<Long> status, Optional<String> description, Optional<String> title);
}

