package com.dddryinside.service;

import com.dddryinside.DTO.Patient;

import java.sql.*;

public class DataBaseAccess {
    private static final String DB_URL = "jdbc:sqlite:./database.db";

    public static void savePatient(Patient patient) {
        checkPatientsTableExist();

        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "INSERT INTO users (name, second_name, additional_name, date_of_birth, gender) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметров запроса
                    stmt.setString(1, patient.getName());
                    stmt.setString(2, patient.getSecondName());
                    stmt.setString(3, patient.getAdditionalName());
                    stmt.setString(4, String.valueOf(patient.getBirthDate()));
                    stmt.setString(5, patient.getSex());

                    // Выполнение запроса
                    stmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkPatientsTableExist() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            // Проверяем, существует ли таблица "users"
            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");
            if (!resultSet.next()) {
                // Если таблица не существует, создаем ее
                statement.execute("CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT," +
                        "second_name TEXT," +
                        "additional_name TEXT," +
                        "date_of_birth DATE," +
                        "gender TEXT)");
                System.out.println("Таблица 'users' успешно создана.");
            } else {
                System.out.println("Таблица 'users' уже существует.");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
}
