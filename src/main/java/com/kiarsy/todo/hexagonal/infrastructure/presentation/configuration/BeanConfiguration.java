package com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration;

import com.kiarsy.todo.hexagonal.core.application.service.ContactService;
import com.kiarsy.todo.hexagonal.core.application.service.TodoService;
import com.kiarsy.todo.hexagonal.core.application.service.UserService;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter.TokenGenerator;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository.ContactRepositoryImplement;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository.TodoRepositoryImplement;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository.UserRepositoryImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    TodoService todoService(TodoRepositoryImplement repository) {
        return new TodoService(repository);
    }

    @Bean
    ContactService contactService(ContactRepositoryImplement repository) {
        return new ContactService(repository);
    }

    @Bean
    UserService userService(UserRepositoryImplement repository, TokenGenerator tokenGenerator) {
        return new UserService(repository, tokenGenerator);
    }
}
