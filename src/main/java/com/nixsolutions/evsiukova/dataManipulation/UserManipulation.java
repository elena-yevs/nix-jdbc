package com.nixsolutions.evsiukova.dataManipulation;

import com.nixsolutions.evsiukova.connection.ConnectionManager;
import com.nixsolutions.evsiukova.creation.TableCreation;
import com.nixsolutions.evsiukova.dao.UserDao;
import com.nixsolutions.evsiukova.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserManipulation extends User implements UserDao {
    // Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/roles-for-people");
    Connection connection = ConnectionManager.connect();

    public UserManipulation() throws SQLException, IOException {
        super();
    }

    @Override
    public void create(User user) throws SQLException, IOException {
        TableCreation tableCreation = new TableCreation();
        tableCreation.connect();
        //Long id, String login, String password, String email, String firstname, String lastname, Date birthday
        String sql = "INSERT INTO person (id, role_id, firstname, lastname, login, dob, password, email) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, 1L);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, "Bruno");
            preparedStatement.setString(4, "Mars");
            preparedStatement.setString(5, "bruno-mars");
            preparedStatement.setString(6, "08.10.1985");
            preparedStatement.setString(7, "bruno22.10mars!");
            preparedStatement.setString(8, "brunomars@gmail.com");

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println("User Bruno Mars created");
    }


    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE person SET id=?, role_id=?, firstname=?, lastname=?, login=?, dob=?, password=?, email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setDate(6, (java.sql.Date) user.getBirthday());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getEmail());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(User user) throws SQLException {
        String sql = "DELETE FROM person WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM person";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setLogin("LOGIN");
                user.setBirthday(resultSet.getDate("BIRTHDAY"));
                user.setPassword("PASSWORD");
                user.setEmail("EMAIL");

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT login FROM person WHERE id = ?";
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT email FROM person WHERE id = ?";
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }
}
