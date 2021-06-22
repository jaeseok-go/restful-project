package com.jaeseok.restfulproject.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
//    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable long id) {
        User user = userService.findOne(id);

        if (user == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserInfo", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn"));

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
//    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable long id) {
        User user = userService.findOne(id);

        if (user == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserInfoV2", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade"));

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
