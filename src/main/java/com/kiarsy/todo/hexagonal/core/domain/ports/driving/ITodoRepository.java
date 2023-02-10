package com.kiarsy.todo.hexagonal.core.domain.ports.driving;

import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoRepository {
    List<Todo> findAllByOwnerId(long owner);

    Optional<Todo> findOneByOwnerIdAndId(long owner, long id);

    Todo save(Todo entity);

    void deleteOne(long owner, long id);
}
