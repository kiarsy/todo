package com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo;

import java.util.Date;

public class TokenUser {
    private long id;
    private String username;

    private String name;

    private Date expire;

    public TokenUser(long id, String username, String name, Date expire) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.expire = expire;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Date getExpire() {
        return expire;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TokenUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", expire=" + expire +
                '}';
    }
}
