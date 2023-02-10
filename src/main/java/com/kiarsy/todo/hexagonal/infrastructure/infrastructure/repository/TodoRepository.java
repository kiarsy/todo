package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;

import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findAllByOwnerId(long owner);

    Optional<TodoEntity> findOneByOwnerIdAndId(long owner, long id);

    Long deleteByOwnerIdAndId(long owner, long id);

}
