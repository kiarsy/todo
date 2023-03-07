package com.kiarsy.todo.hexagonal.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Optional;

public class Todo {
    private Long id;
    private String title;
    private String description;
    private TodoStatus status = TodoStatus.Not_Completed;
    private Date date;
    @JsonIgnore
    private User owner;

    protected Todo() {
    }

    public Todo(Long id, String title, String description, TodoStatus status, Date date, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public User getOwner() {
        return owner;
    }


    // Business Logic Related Methods
    // Entity state changes through these methods
    public void createNewTodo(Long id) {
        this.owner = new User(id,null,null,null,null);
        this.date = new Date();
    }

    public void editTodo(Optional<Long> status, Optional<String> description, Optional<String> title) {
        if (status.isPresent()) {
            var statusEnum = TodoStatus.getByValue(status.get());
            this.status = statusEnum;
        }
        if (description.isPresent()) {
            this.description = description.get();
        }
        if (title.isPresent()) {
            this.title = title.get();
        }
    }

}
