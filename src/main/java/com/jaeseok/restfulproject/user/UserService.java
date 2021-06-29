package com.jaeseok.restfulproject.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1L, "JaeSeok", new Date(), "pass1", "121212-1212121"));
        users.add(new User(2L, "John", new Date(), "pass2", "232323-2323232"));
        users.add(new User(3L, "Michal", new Date(), "pass3", "343434-3434343"));
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

        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User update(User user){

        for(User updatedUser : users){
            if(updatedUser.getId() == user.getId()) {
                updatedUser.setName(user.getName());
                updatedUser.setJoinDate(user.getJoinDate());
                return updatedUser;
            }
        }
        return null;
    }

    public User deleteById(long id) {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
