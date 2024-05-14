package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.JPA.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOService {

    //JPA/Hibernate --> Database
    @Autowired
    private final UserRepository repository;

    public UserDAOService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
       this.repository.save(user);
       return user;
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public Optional<User> findById(int id) {
        return this.repository.findById(id);
    }

    public void deleteById(int id) {
        this.repository.deleteById(id);
    }
}
