package com.dddryinside.service;

import com.dddryinside.DTO.DASS21;
import com.dddryinside.DTO.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestsDataBaseAccess extends DataBaseAccess{
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

    public static List<DASS21> getDASS21Results(Patient patient) {
        checkDASS21TableExist();
        List<DASS21> dass21List = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dass21 WHERE patient_id = ?")) {
                stmt.setInt(1, patient.getId());
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    LocalDate date = resultSet.getObject("test_date", LocalDate.class);
                    int depression = resultSet.getInt("depression");
                    int anxiety = resultSet.getInt("anxiety");
                    int stress = resultSet.getInt("stress");

                    DASS21 dass21 = new DASS21(date, depression, anxiety, stress);
                    dass21List.add(dass21);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Более подробная обработка ошибок
            e.printStackTrace();
        }

        return dass21List;
    }
}
