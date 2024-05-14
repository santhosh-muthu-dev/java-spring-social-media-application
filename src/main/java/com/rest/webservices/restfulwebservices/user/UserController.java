package com.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserDAOService service;

    public UserController(UserDAOService userDAOService) {
        this.service = userDAOService;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> GetAllUsers() {
       return this.service.findAll();
    }

    //GET /users/id
    @GetMapping("/users/{id}")
    public Optional<User> GetUsers(@PathVariable int id) {
        Optional<User> user = this.service.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id:"+id);
        }
        return user;
    }

    //DELETE /users/id
    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        this.service.deleteById(id);
    }

    //POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody List<User> users) {
        User savedUser = new User();
        for(User user : users) savedUser = this.service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(savedUser.getId())
                       .toUri();
        return ResponseEntity.created(location).build();
    }
}
