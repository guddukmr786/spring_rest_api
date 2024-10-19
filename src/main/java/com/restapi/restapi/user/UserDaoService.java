package com.restapi.restapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //communicate with db or static user list
    private static final List<UserBean> users = new ArrayList<>();
    private static int userId = 0;
    static {
        users.add(new UserBean(++userId, "Guddu Kumar", LocalDate.now().minusYears(30)));
        users.add(new UserBean(++userId, "Prabhakar Kumar", LocalDate.now().minusYears(25)));
        users.add(new UserBean(++userId, "Aslam Khan", LocalDate.now().minusYears(20)));
    }
    //list the users
    public List<UserBean> userList(){
        return users;
    }

    //find user
    public UserBean findOne(int id){
        Predicate<? super UserBean> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    //Save the data
    public UserBean save(UserBean user) {
        user.setId(++userId);
        users.add(user);
        return user;
    }

}
