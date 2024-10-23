package com.restapi.restapi.user;

import com.restapi.restapi.exception.UserNotFoundException;
import com.restapi.restapi.jpa.PostRepository;
import com.restapi.restapi.jpa.UserRepository;
import com.restapi.restapi.post.Post;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    private final UserRepository repository;
    private final PostRepository postRepository;

    public UserJpaController(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findOne(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty() )
            throw new UserNotFoundException("id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUser());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("jpa/users/{id}/post")
    public List<Post> getPostByUserId(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("id:"+id);
        return user.get().getPosts();
    }

    @PostMapping("jpa/users/{id}/post")
    public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("id:"+id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
