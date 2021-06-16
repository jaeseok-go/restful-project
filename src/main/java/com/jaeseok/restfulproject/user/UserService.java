package com.jaeseok.restfulproject.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1L, "JaeSeok", new Date()));
        users.add(new User(2L, "John", new Date()));
        users.add(new User(3L, "Michal", new Date()));
    }


    public User save(User user) {
        if(user.getId() == null) {
            user.setId((long) users.size() + 1);
        }
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(long id) {
        for(User user : users) {
            if(user.getId() == id) return user;
        }
        return null;
    }
}
