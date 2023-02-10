package com.kiarsy.todo.hexagonal.core.application.service;

import com.kiarsy.todo.hexagonal.core.application.exception.ResourceNotFoundException;
import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.IContactService;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.IContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ContactService implements IContactService {
    IContactRepository contactRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void create(long owner_id, Contact contact) {
        contact.create(owner_id);
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> listAllOfUser(long owner_id) {
        return contactRepository.findAllByOwnerId(owner_id);
    }

    @Override
    public void update(long owner_id, long id, Optional<String> firstName, Optional<String> lastName, Optional<String> phoneNumber) {
        var entity = contactRepository.findOneByOwnerIdAndId(owner_id, id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Contact not found");
        }
        var todo = entity.get();
        todo.editTodo(firstName, lastName, phoneNumber);
        contactRepository.save(todo);
    }

    @Override
    public void delete(long owner_id, long id) {
        contactRepository.deleteOne(owner_id, id);
    }
}
