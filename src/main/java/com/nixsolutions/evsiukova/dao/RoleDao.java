package com.nixsolutions.evsiukova.dao;

import com.nixsolutions.evsiukova.entity.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    void create() throws SQLException, IOException;
    void update() throws SQLException;
    void remove() throws SQLException;
    List<Role> findByRoleName(String roleName) throws SQLException;
}
