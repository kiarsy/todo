package com.kiarsy.todo.hexagonal.infrastructure.presentation.controller;

import com.kiarsy.todo.hexagonal.core.application.service.ContactService;
import com.kiarsy.todo.hexagonal.core.domain.entities.Contact;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.IContactService;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration.JwtAuthentication;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/contact")
@Transactional
public class ContactController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IContactService service;

    @Autowired
    public ContactController(ContactService todoService) {
        this.service = todoService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Response list() {
        var lst = service.listAllOfUser(JwtAuthentication.getAuthentication().getId());
        return new Response<>(lst);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Response create(@RequestBody Contact contact) {
        var newContact = service.create(JwtAuthentication.getAuthentication().getId(), contact);
        return new Response(newContact);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable(value = "id") long id) {
        service.delete(JwtAuthentication.getAuthentication().getId(), id);
        return new Response();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Response update(@RequestBody Map<String, Object> body, @PathVariable(value = "id") long id) {
        Optional<String> firstName = Optional.ofNullable(null);
        Optional<String> lastName = Optional.ofNullable(null);
        Optional<String> phoneNumber = Optional.ofNullable(null);
        if (body.get("firstName") != null) {
            firstName = Optional.of(String.valueOf(String.valueOf(body.get("firstName"))));
        }
        if (body.get("lastName") != null) {
            lastName = Optional.of(String.valueOf(body.get("lastName")));
        }
        if (body.get("phoneNumber") != null) {
            phoneNumber = Optional.of(String.valueOf(body.get("phoneNumber")));
        }

        var entity = service.update(JwtAuthentication.getAuthentication().getId(), id, firstName, lastName, phoneNumber);
        return new Response(entity);
    }

}