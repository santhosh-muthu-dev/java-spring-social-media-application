package com.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    public UserDAOService service;

    public UserResource(UserDAOService service) {
        this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> GetAllUsers() {
       return service.findAll();
    }

    //GET /users/id
    @GetMapping("/users/{id}")
    public User GetUsers(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id:"+id);
        }
        return user;
    }

    //DELETE /users/id
    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    //POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(savedUser.getId())
                       .toUri();
        return ResponseEntity.created(location).build();
    }
}
