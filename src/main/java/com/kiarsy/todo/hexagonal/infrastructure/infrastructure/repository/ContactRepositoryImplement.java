package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;

import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;
import com.kiarsy.todo.hexagonal.core.domain.ports.driving.IContactRepository;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ContactRepositoryImplement implements IContactRepository {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactRepositoryImplement(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAllByOwnerId(long owner) {
        return contactRepository.findAllByOwnerId(owner)
                .stream()
                .map(ContactEntity::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Contact> findOneByOwnerIdAndId(long owner, long id) {
        return contactRepository.findOneByOwnerIdAndId(owner, id)
                .stream()
                .map(ContactEntity::toDomainEntity)
                .findFirst();
    }

    @Override
    public Contact save(Contact entity) {
        return ContactEntity.toDomainEntity(contactRepository.save(new ContactEntity(entity)));
    }

    @Override
    public void deleteOne(long owner, long id) {
        contactRepository.deleteByOwnerIdAndId(owner, id);
    }
}
