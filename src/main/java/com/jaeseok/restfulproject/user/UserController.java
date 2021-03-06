package com.jaeseok.restfulproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);

        URI location =
                ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable long id) {
        User user = userService.findOne(id);

        if (user == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));

        return entityModel;
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);

        if(user == null) throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        User user = userService.deleteById(id);

        if(user == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
}
