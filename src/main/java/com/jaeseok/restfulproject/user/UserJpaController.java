package com.jaeseok.restfulproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable(name = "id") long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(builder.withRel("all-users"));

        return entityModel;
    }
}