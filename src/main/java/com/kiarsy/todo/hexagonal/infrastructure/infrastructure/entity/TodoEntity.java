package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kiarsy.todo.hexagonal.core.domain.entities.Todo;
import com.kiarsy.todo.hexagonal.core.domain.entities.TodoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "title is Empty")
    private String title;
    @NotEmpty(message = "description is Empty")
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private TodoStatus status = TodoStatus.Not_Completed;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner;
    @Column(name = "owner_id")
    private Long owner_id;

    protected TodoEntity() {
    }

    public TodoEntity(Long id, String title, String description, TodoStatus status, Date date, UserEntity owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
        this.owner = owner;
    }

    public TodoEntity(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.status = todo.getStatus();
        this.date = todo.getDate();
        this.owner = new UserEntity(todo.getOwner());
        this.owner_id = this.owner.getId();
    }

    public static Todo toTodo(TodoEntity todoEntity)
    {
        if (todoEntity==null)
            return null;
        return new Todo(todoEntity.getId(),todoEntity.getTitle(),todoEntity.getDescription(),todoEntity.getStatus(),todoEntity.getDate(),UserEntity.toUser(todoEntity.getOwner()));
    }
    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOwner(UserEntity owner) {
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

    public UserEntity getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", owner=" + owner +
                ", owner_id=" + owner_id +
                '}';
    }
}
