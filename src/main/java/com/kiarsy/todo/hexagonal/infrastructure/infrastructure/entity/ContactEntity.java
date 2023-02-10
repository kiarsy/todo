package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity(name = "Contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "firstName is Empty")
    private String firstName;
    @NotEmpty(message = "lastName is Empty")

    private String lastName;
    @NotEmpty(message = "phoneNumber is Empty")

    private String phoneNumber;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner;
    @Column(name = "owner_id")
    private Long owner_id;

    protected ContactEntity() {
    }

    public ContactEntity(Long id, String firstName, String lastName, String phoneNumber, UserEntity owner) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
    }

    public ContactEntity(Contact contact) {
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.phoneNumber = contact.getPhoneNumber();
        this.owner = new UserEntity(contact.getOwner());
        this.owner_id = this.owner.getId();
    }

    public static Contact toDomainEntity(ContactEntity contactEntity) {
        if (contactEntity == null)
            return null;
        return new Contact(contactEntity.getId(), contactEntity.getFirstName(), contactEntity.getLastName(), contactEntity.getPhoneNumber(), UserEntity.toUser(contactEntity.getOwner()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }
}
