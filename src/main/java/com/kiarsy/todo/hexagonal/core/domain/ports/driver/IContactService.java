package com.kiarsy.todo.hexagonal.core.domain.ports.driver;

import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    public List<Contact> listAllOfUser(long owner_id);

    public Contact create(long owner_id, Contact contact);

    public void delete(long owner_id,long id);

    public Contact update(long owner_id, long id, Optional<String> firstName, Optional<String> lastName, Optional<String> phoneNumber);

}
