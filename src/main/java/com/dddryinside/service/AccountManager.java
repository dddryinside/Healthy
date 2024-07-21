package com.dddryinside.service;

import com.dddryinside.models.User;
import com.dddryinside.pages.LogInPage;

import java.sql.*;

public class AccountManager {
    private static final String DB_URL = "jdbc:sqlite:./mental.db";
    private static User user;

    //private static User user = new User("Иванов", "Иван", "Иванович", LocalDate.now(), 1, "test", "123456789");

    public static void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isUserTableExists();

            String insertQuery = "INSERT INTO user (name, second_name, additional_name, username, password) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSecondName());
                statement.setString(3, user.getAdditionalName());
                statement.setString(4, user.getUsername());
                statement.setString(5, user.getPassword());
                statement.executeUpdate();

                try {
                    logIn(user.getUsername(), user.getPassword());
                } catch (Exception e) {
                    PageManager.showNotification(e.getMessage());
                }
            }
        } catch (SQLException e) {
            PageManager.showNotification("Data base error!");
        }
    }

    public static void updateUser(User newUser) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE user SET name = ?, second_name = ?, additional_name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, newUser.getName());
                statement.setString(2, newUser.getSecondName());
                statement.setString(3, newUser.getAdditionalName());
                statement.setInt(4, user.getId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 1) {
                    try {
                        logIn(user.getUsername(), user.getPassword());
                    } catch (Exception e) {
                        PageManager.showNotification(e.getMessage());
                    }
                } else {
                    throw new RuntimeException("Data base error!");
                }
            }
        } catch (SQLException e) {
            PageManager.showNotification("Data base error!");
        }
    }

    public static void updatePassword(String newPassword) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE user SET password = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newPassword);
                statement.setInt(2, user.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            PageManager.showNotification("Data base error!");
        }

    }

    public static void logIn(String username, String password) throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isUserTableExists();

            String insertQuery = "SELECT id, name, second_name, additional_name, username, password FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String secondName = resultSet.getString("second_name");
                        String additionalName = resultSet.getString("additional_name");

                        AccountManager.user = new User(id, name, secondName, additionalName, username, password);
                    } else {
                        throw new Exception("not_found");
                    }
                }
            }
        } catch (SQLException e) {
            PageManager.showNotification("Data base error!");
        }
    }

    public static void logOut() {
        user = null;
        PageManager.loadPage(new LogInPage());
    }

    public static boolean isUsernameAvailable(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isUserTableExists();

            String insertQuery = "SELECT id FROM user WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return !resultSet.next();
                }
            }
        } catch (SQLException e) {
            PageManager.showNotification("Data base error!");
            return false;
        }
    }

    private static void isUserTableExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            if (!tableExists(connection)) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS user (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "second_name TEXT," +
                        "additional_name TEXT," +
                        "username TEXT," +
                        "password TEXT)";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean tableExists(Connection connection) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet resultSet = metadata.getTables(null, null, "user", null);
        return resultSet.next();
    }

    public static User getUser() {
        return AccountManager.user;
    }
}
