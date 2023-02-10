package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity(name = "user")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}, name = "UK_username")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name must be provided")
    @NotEmpty(message = "Name must be provided")
    private String name;

    @NotEmpty(message = "Username must be provided")
    private String username;
    @NotNull(message = "Password must be provided")
    @NotEmpty(message = "Password must be provided")
    private String password;
    @NotNull(message = "Email must be provided")
    @NotEmpty(message = "Email must be provided")
    @Email(message = "Email format is wrong")
    private String email;
    //5763
    @OneToMany(mappedBy = "owner")
    private Set<TodoEntity> todos;

    protected UserEntity() {
    }

    public UserEntity(Long id, String name, String username, String password, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public static User toUser(UserEntity userEntity)
    {
        if (userEntity == null)
            return null;
        return new User(userEntity.getId(),userEntity.getName(),userEntity.getUsername(),userEntity.getPassword(),userEntity.getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
