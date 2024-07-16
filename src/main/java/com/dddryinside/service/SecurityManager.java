package com.dddryinside.service;

import com.dddryinside.models.User;

import java.sql.*;
import java.time.LocalDate;

public class SecurityManager {
    private static final String DB_URL = "jdbc:sqlite:./mental.db";
    private static User user;

    public static void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isUserTableExists();

            String insertQuery = "INSERT INTO user (name, second_name, additional_name, birth_date, gender, username, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSecondName());
                statement.setString(3, user.getAdditionalName());
                statement.setString(4, String.valueOf(user.getBirthDate()));
                statement.setInt(5, user.getGender());
                statement.setString(6, user.getUsername());
                statement.setString(7, user.getPassword());
                statement.executeUpdate();

                logIn(user.getUsername(), user.getPassword());
            }
        } catch (SQLException e) {
            PageManager.errorNotification("Ошибка базы данных!");
        }
    }

    public static void updateUser(User newUser) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE user SET name = ?, second_name = ?, additional_name = ?, birth_date = ?, gender = ?, password = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, newUser.getName());
                statement.setString(2, newUser.getSecondName());
                statement.setString(3, newUser.getAdditionalName());
                statement.setString(4, String.valueOf(newUser.getBirthDate()));
                statement.setInt(5, newUser.getGender());
                statement.setString(6, newUser.getPassword());
                statement.setInt(7, user.getId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 1) {
                    logIn(user.getUsername(), newUser.getPassword());
                } else {
                    throw new RuntimeException("Не удалось обновить запись!");
                }
            }
        } catch (SQLException e) {
            PageManager.errorNotification("Ошибка базы данных!");
        }
    }

    public static void logIn(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isUserTableExists();

            String insertQuery = "SELECT id, name, second_name, additional_name, birth_date, gender, username, password FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String secondName = resultSet.getString("second_name");
                        String additionalName = resultSet.getString("additional_name");
                        LocalDate birthDate = LocalDate.parse(resultSet.getString("birth_date"));
                        int gender = resultSet.getInt("gender");

                        SecurityManager.user = new User(id, name, secondName, additionalName, birthDate, gender, username, password);
                    } else {
                        PageManager.errorNotification("Пользователь не найден!");
                    }
                }
            }
        } catch (SQLException e) {
            PageManager.errorNotification("Ошибка базы данных!");
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
                        "birth_date DATETIME," +
                        "gender INT," +
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

    User getUser() {
        return SecurityManager.user;
    }
}
