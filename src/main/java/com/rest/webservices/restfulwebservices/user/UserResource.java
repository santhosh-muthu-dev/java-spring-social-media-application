package com.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.*;
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
        return service.findOne(id);
    }

    //POST /users
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        service.saveUser(user);
    }
}
