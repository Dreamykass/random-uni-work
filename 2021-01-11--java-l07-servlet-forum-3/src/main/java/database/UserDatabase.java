package database;

import datatype.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDatabase {
    private static final Lock usersFileLock = new ReentrantLock();
    public static String errors = "";

    private static List<User> getAllUsersNoLocking() {
        List<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                User user = new User();
                user.login = resultSet.getString("login");
                user.password = resultSet.getString("password");
                user.email = resultSet.getString("email");
                users.add(user);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            errors = e.toString();
        }

        return users;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        usersFileLock.lock();
        users = getAllUsersNoLocking();
        usersFileLock.unlock();
        return users;
    }

    public static Boolean insertUserIfPossible(User user) {
        usersFileLock.lock();

        try {
            List<User> users = getAllUsersNoLocking();
            users.add(user);

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");
                Statement statement = connection.createStatement();

                String query = "INSERT INTO users (login, password, email) " +
                        "VALUES ('" + user.login + "', '" + user.password + "', '" + user.email + "');";
                statement.executeUpdate(query);

            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                errors = e.toString();
                usersFileLock.unlock();
                return false;
            }

            usersFileLock.unlock();
            return true;

        } catch (Exception ignored) {
            usersFileLock.unlock();
            return false;
        }
    }
}
