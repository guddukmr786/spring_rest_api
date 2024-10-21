package com.restapi.restapi.user;

import com.restapi.restapi.exception.UserNotFoundException;
import jakarta.validation.Valid;
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
    public List<User> getAllUser() {
        return this.service.userList();
    }
    @GetMapping("/users/{id}")
    public User findOne(@PathVariable int id) throws UserNotFoundException {
        User user = this.service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id:"+id);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        this.service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =this.service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
