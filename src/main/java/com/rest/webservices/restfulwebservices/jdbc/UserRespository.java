package com.rest.webservices.restfulwebservices.jdbc;

import com.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class UserRespository {

    @Autowired
    private JdbcTemplate jdbc;

    private static String USER_INSERT_QUERY = """
       Insert into users(id, name, birthdate)
           values(?,?,?);
    """;

    private static String DELETE_QUERY = """
       Delete from users where id = ?
    """;

    private static String SELECT_QUERY = """
       Select * from users where id = ?
    """;

    public void insert(User user) {
        jdbc.update(USER_INSERT_QUERY, user.getId(), user.getName(), user.getBirthdate());
    }

    public void deleteById(int id) {
        jdbc.update(DELETE_QUERY, id);
    }

    public User findByID(int id) {
        return jdbc.queryForObject(SELECT_QUERY, (rs, rowNum) ->
                new User(rs.getInt("id"),
                         rs.getNString("name"),
                         LocalDate.parse(rs.getString("birthdate"))), id);
    }
}
