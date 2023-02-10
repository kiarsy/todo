package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;


import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query(value = "SELECT * FROM user WHERE (username like ?1) and (BINARY password = ?2)",nativeQuery = true)
    UserEntity login(String username, String password);
}
