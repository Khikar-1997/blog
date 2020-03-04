package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class UserService {

    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/newspaper", "root", "root")) {
            String query = "INSERT INTO users(username,password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<User> findAllUser() {
        ArrayList<User> userList = new ArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/newspaper", "root", "root")) {
            String query = "SELECT* FROM users ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public User findUserById(int id) {
        User user = new User();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/newspaper", "root", "root")) {
            String query = "SELECT* FROM users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public void deleteUserByID(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/newspaper", "root", "root")) {
            String query = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateUser(int id, User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/newspaper", "root", "root")) {
            String query = "UPDATE users SET username=? ,password=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
