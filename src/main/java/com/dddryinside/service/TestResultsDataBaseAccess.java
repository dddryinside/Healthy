package com.dddryinside.service;

import java.sql.*;
import java.time.LocalDate;

public class TestResultsDataBaseAccess extends DataBaseAccess{
    private static void checkDASS21TableExist() {

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='dass21'");
            if (!resultSet.next()) {
                // Если таблица не существует, создаем ее
                statement.execute("CREATE TABLE dass21 (" +
                        "id INTEGER PRIMARY KEY," +
                        "patient_id INT," +
                        "test_date DATE," +
                        "depression INT," +
                        "anxiety INT," +
                        "stress INT)");
                System.out.println("Таблица 'dass21' успешно создана.");
            } else {
                System.out.println("Таблица 'dass21' уже существует.");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    public static void saveDASS21Result(int patient_id, int depression, int anxiety, int stress) {
        checkDASS21TableExist();

        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "INSERT INTO dass21 (patient_id, test_date, depression, anxiety, stress) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметров запроса
                    stmt.setString(1, String.valueOf(patient_id));
                    stmt.setString(2, String.valueOf(LocalDate.now()));
                    stmt.setString(3, String.valueOf(depression));
                    stmt.setString(4, String.valueOf(anxiety));
                    stmt.setString(5, String.valueOf(stress));

                    // Выполнение запроса
                    stmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
