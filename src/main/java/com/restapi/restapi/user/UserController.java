package com.restapi.restapi.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<UserBean> getAllUser() {
        return this.service.userList();
    }
    @GetMapping("/users/{id}")
    public UserBean findOne(@PathVariable int id) throws UserNotFoundException {
        UserBean user = this.service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id:"+id);
        return user;
    }
    @PostMapping("/users")
    public ResponseEntity<UserBean> createUser(@RequestBody UserBean user){
        UserBean savedUser =this.service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
