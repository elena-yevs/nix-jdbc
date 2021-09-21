package com.nixsolutions.evsiukova.dao;

import com.nixsolutions.evsiukova.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException, IOException;

    void update(User user) throws SQLException;

    void remove(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findByLogin(String login) throws SQLException;

    User findByEmail(String email) throws SQLException;
}
