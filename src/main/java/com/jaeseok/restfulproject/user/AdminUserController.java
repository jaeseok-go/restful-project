package com.jaeseok.restfulproject.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = userService.findAll();

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserInfo", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn"));

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable long id) {
        User user = userService.findOne(id);

        if (user == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserInfo", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn"));

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
