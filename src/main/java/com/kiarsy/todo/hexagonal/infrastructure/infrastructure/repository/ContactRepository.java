package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.repository;

import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findAllByOwnerId(long owner);

    Optional<ContactEntity> findOneByOwnerIdAndId(long owner, long id);

    Long deleteByOwnerIdAndId(long owner, long id);
}
