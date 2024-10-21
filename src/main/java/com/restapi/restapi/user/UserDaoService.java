package com.restapi.restapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //communicate with db or static user list
    private static final List<User> users = new ArrayList<>();
    private static int userId = 0;
    static {
        users.add(new User(++userId, "Guddu Kumar", LocalDate.now().minusYears(30)));
        users.add(new User(++userId, "Prabhakar Kumar", LocalDate.now().minusYears(25)));
        users.add(new User(++userId, "Aslam Khan", LocalDate.now().minusYears(20)));
    }
    //list the users
    public List<User> userList(){
        return users;
    }

    //find user
    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    //Save the data
    public User save(User user) {
        user.setId(++userId);
        users.add(user);
        return user;
    }

    //delete user
    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
