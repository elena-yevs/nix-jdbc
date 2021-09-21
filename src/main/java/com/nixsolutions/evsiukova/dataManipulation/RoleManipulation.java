package com.nixsolutions.evsiukova.dataManipulation;

import com.nixsolutions.evsiukova.connection.ConnectionManager;
import com.nixsolutions.evsiukova.creation.TableCreation;
import com.nixsolutions.evsiukova.dao.RoleDao;
import com.nixsolutions.evsiukova.entity.Role;
import com.nixsolutions.evsiukova.remover.Remover;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleManipulation extends Role implements RoleDao {
    public static final Logger LOG = Logger.getLogger(Remover.class.getName());
    Connection connection = ConnectionManager.connect();

    public RoleManipulation() throws SQLException, IOException {
    }

    @Override
    public void create() throws SQLException, IOException {
        String sql = "INSERT INTO roles (id, roleName) " +
                "VALUES(0, 'unauthorized user'), (1, 'admin'), (2, 'user')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        LOG.debug("First Role created!"); //DELETE
    }

    @Override
    public void update() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE roles SET roleName = ? WHERE id = 0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "guest");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("update worked"); //DELETE
    }

    @Override
    public void remove() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM roles WHERE id = 0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println("drop roles");
    }

    @Override
    public List<Role> findByRoleName(String roleName) throws SQLException {
        List<Role> roleList = new ArrayList<>();

         Statement statement = connection.createStatement();
        String sql = "SELECT id FROM roles WHERE roleName = 'admin'";
        //PreparedStatement preparedStatement =  connection.prepareStatement(sql);
       // ResultSet resultSet = statement.executeQuery(sql);
       /* while(resultSet.next()) {
           // System.out.println(resultSet.getInt(id));
            System.out.println(resultSet.getString("id"));
        }
        resultSet.close();
        statement.close();*/
        assert false;

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }





        ////////////////////////////
       /* try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, rolename);
            ResultSet resultSet = preparedStatement.executeQuery();

            role.setRoleName(resultSet.getString("rolename"));
            role.setId(resultSet.getInt("id"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println(" role " + role);*/
        return  roleList;

    }


}
