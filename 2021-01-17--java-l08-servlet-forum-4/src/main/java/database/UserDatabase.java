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
                user.id = resultSet.getInt("id");
                user.login = resultSet.getString("login");
                user.password = resultSet.getString("password");
                user.email = resultSet.getString("email");
                user.type = resultSet.getString("type");
                user.blocked = resultSet.getBoolean("blocked");
                user.passwordChange = resultSet.getBoolean("passwordChange");
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

                String query = "INSERT INTO users (login, password, email, type, blocked, passwordChange) " +
                        "VALUES ('" + user.login + "', '" + user.password + "', '" + user.email
                        + "', '" + user.type + "', '" + (user.blocked ? 1 : 0)
                        + "', '" + (user.passwordChange ? 1 : 0) + "');";
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

    public static void deleteUserById(int id) {
        usersFileLock.lock();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");
            Statement statement = connection.createStatement();

            String query = "DELETE FROM users WHERE id='" + id + "';";
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            errors = e.toString();
        }

        usersFileLock.unlock();
    }
}
