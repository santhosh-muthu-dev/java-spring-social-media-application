package com.rest.webservices.restfulwebservices.jdbc;

import com.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRespository userRespository;

    @Override
    public void run(String... args) throws Exception {
        userRespository.insert(new User(1, "Adam", LocalDate.now().minusYears(30)));
        userRespository.insert(new User(2, "Eve", LocalDate.now().minusYears(20)));
        userRespository.insert(new User(3, "Jim", LocalDate.now().minusYears(25)));

        userRespository.deleteById(1);
        System.out.println(userRespository.findByID(2));
        System.out.println(userRespository.findByID(3));
    }
}
