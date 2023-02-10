package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.IUserRepository;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImplement implements IUserRepository {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        return UserEntity.toUser(userRepository.login(username, password));
    }

    @Override
    public User save(User entity) {
        return UserEntity.toUser(userRepository.save(new UserEntity(entity)));
    }
}
