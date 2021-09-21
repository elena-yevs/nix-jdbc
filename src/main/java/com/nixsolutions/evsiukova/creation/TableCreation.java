package com.nixsolutions.evsiukova.creation;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TableCreation {
    private static Connection connection = null;
    private static final String pathToDbPropertiesFile = "C:\\nix-project\\elena.evsiukova\\17_sql_jdbc\\src\\main" +
            "\\java\\com\\nixsolutions\\evsiukova\\jdbc.properties";

    public static void main(String[] args) throws SQLException, IOException {
        //Class.forName("org.postgresql.Driver");
        TableCreation tableCreation = new TableCreation();
        tableCreation.connect();
        tableCreation.createTableRoles();
        tableCreation.createTableUsers();
        connection.close();
    }

    public Connection connect() throws SQLException, IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(pathToDbPropertiesFile);
        properties.load(fileReader);
        String url = properties.getProperty("db.conn.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public void createTableUsers() throws SQLException {
        Statement statement = connection.createStatement();
        // statement.executeUpdate("DROP TABLE IF EXISTS  person ");
        statement.executeUpdate("CREATE TABLE  users ( " +
                "id INT NOT NULL PRIMARY KEY,"
                + "role_id INT NOT NULL,"
                + "FOREIGN KEY (role_id) REFERENCES roles (id),"
                + "username VARCHAR(256) NOT NULL UNIQUE,"
                + "firstname VARCHAR(256) NOT NULL,"
                + "lastname VARCHAR(256) NOT NULL,"
                + "email VARCHAR(256) NOT NULL,"
                + "dob VARCHAR(256) NOT NULL,"
                + "password VARCHAR(256) NOT NULL)");
    }


    public void createTableRoles() throws SQLException {
        Statement statement = connection.createStatement();
        //statement.executeUpdate("DROP TABLE IF EXISTS  role ");
        statement.executeUpdate("CREATE TABLE  roles ( " +
                "id INT NOT NULL PRIMARY KEY,"
                + "roleName VARCHAR(256) NOT NULL)");
        statement.close();
    }
}
