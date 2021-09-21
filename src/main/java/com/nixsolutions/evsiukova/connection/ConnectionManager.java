package com.nixsolutions.evsiukova.connection;

import com.nixsolutions.evsiukova.dataManipulation.RoleManipulation;
import com.nixsolutions.evsiukova.dataManipulation.UserManipulation;
import com.nixsolutions.evsiukova.entity.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection = null;
    private static final String pathToDbPropertiesFile = "C:\\nix-project\\elena.evsiukova\\17_sql_jdbc\\src\\main" +
            "\\java\\com\\nixsolutions\\evsiukova\\jdbc.properties";

    public static void main(String[] args) throws SQLException, IOException {
        connect();
        RoleManipulation roleManipulation = new RoleManipulation();
        roleManipulation.create();
        roleManipulation.update();
        roleManipulation.remove();
        roleManipulation.findByRoleName("admin");
        connection.close();
    }

    public static Connection connect() throws SQLException, IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(pathToDbPropertiesFile);
        properties.load(fileReader);
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setServerName(properties.getProperty("db.driver.class"));
        dataSource.setUrl(properties.getProperty("db.conn.url"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        connection = dataSource.getConnection();
        return connection;
    }
}
