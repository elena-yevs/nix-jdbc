package com.nixsolutions.evsiukova.remover;

import com.nixsolutions.evsiukova.creation.TableCreation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Remover {
    public static final Logger LOG = Logger.getLogger(Remover.class.getName());
    static TableCreation tableCreation = new TableCreation();

    public static void main(String[] args) throws SQLException, IOException {
        // Connection connection = DriverManager.getConnection(url, user, password);
        Connection connection = tableCreation.connect();
        Statement statement = connection.createStatement();
        String dropUsers = "DROP TABLE users";
        statement.executeUpdate(dropUsers);

        String dropRoles = "DROP TABLE roles";
        statement.executeUpdate(dropRoles);

        statement.close();
        connection.close();
        LOG.debug("Tables are dropped");
    }
}
