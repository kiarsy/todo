package com.kiarsy.todo.hexagonal.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;

public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @JsonIgnore()
    private User owner;

    protected Contact() {
    }

    public Contact(Long id, String firstName, String lastName, String phoneNumber, User owner) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User getOwner() {
        return owner;
    }

    // Business Logic Related Methods
    // Entity state changes through these methods
    public void create(Long owner_id) {
        this.owner = new User(owner_id,null,null,null,null);
    }

    public void editTodo(Optional<String> firstName, Optional<String> lastName, Optional<String> phoneNumber) {
        if (firstName.isPresent()) {
            this.firstName = firstName.get();
        }
        if (lastName.isPresent()) {
            this.lastName = lastName.get();
        }
        if (phoneNumber.isPresent()) {
            this.phoneNumber = phoneNumber.get();
        }
    }

}
