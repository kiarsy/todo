package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;

import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.ITodoRepository;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TodoRepositoryImplement implements ITodoRepository {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoRepositoryImplement(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findAllByOwnerId(long owner) {
        return todoRepository.findAllByOwnerId(owner)
                .stream()
                .map(TodoEntity::toTodo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Todo> findOneByOwnerIdAndId(long owner, long id) {
        return todoRepository.findOneByOwnerIdAndId(owner, id)
                .stream()
                .map(TodoEntity::toTodo)
                .findFirst();
    }

    @Override
    public Todo save(Todo entity) {
        return TodoEntity.toTodo(todoRepository.save(new TodoEntity(entity)));
    }

    @Override
    public void deleteOne(long owner, long id) {
        todoRepository.deleteByOwnerIdAndId(owner,id);
    }
}
