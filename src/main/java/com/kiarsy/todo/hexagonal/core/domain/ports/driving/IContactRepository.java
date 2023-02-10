package com.kiarsy.todo.hexagonal.core.domain.ports.driving;

import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactRepository {
    List<Contact> findAllByOwnerId(long owner);

    Optional<Contact> findOneByOwnerIdAndId(long owner, long id);

    Contact save(Contact entity);

    void deleteOne(long owner, long id);
}
