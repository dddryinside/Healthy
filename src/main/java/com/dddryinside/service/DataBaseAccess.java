package com.dddryinside.service;

import com.dddryinside.DTO.Patient;
import com.dddryinside.DTO.Tests;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAccess {
    public static final String DB_URL = "jdbc:sqlite:./database.db";

    public static void savePatient(Patient patient) {
        checkPatientsTableExist();

        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "INSERT INTO users (name, second_name, additional_name, date_of_birth, gender, password) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметров запроса
                    stmt.setString(1, patient.getName());
                    stmt.setString(2, patient.getSecondName());
                    stmt.setString(3, patient.getAdditionalName());
                    stmt.setString(4, String.valueOf(patient.getBirthDate()));
                    stmt.setString(5, patient.getSex());
                    stmt.setString(6, patient.getPassword());

                    // Выполнение запроса
                    stmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkPatientsTableExist() {

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
                        "gender TEXT," +
                        "password TEXT)");
                System.out.println("Таблица 'users' успешно создана.");
            } else {
                System.out.println("Таблица 'users' уже существует.");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

    public static List<Patient> getAllPatients() {
        checkPatientsTableExist();
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String secondName = resultSet.getString("second_name");
                String additionalName = resultSet.getString("additional_name");
                LocalDate dateOfBirth = resultSet.getObject("date_of_birth", LocalDate.class);
                String gender = resultSet.getString("gender");

                Patient patient = new Patient(id, name, secondName, additionalName, dateOfBirth, gender);
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }

        return patients;
    }

     public static List<Tests> getAllTestsOfPatient(Patient patient) {
        List<Tests> tests = new ArrayList<>();

        for (Tests currentTest : Tests.values()) {
            if (hasThePatientBeenTested(currentTest.getName(), patient.getId())) {
                tests.add(currentTest);
            }
        }

        return tests;
     }

    private static boolean hasThePatientBeenTested(String tableName, int patientId) {
        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE patient_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметра запроса
                    stmt.setInt(1, patientId);

                    // Выполнение запроса
                    ResultSet rs = stmt.executeQuery();
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPasswordExist(String password) {

        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "SELECT COUNT(*) FROM users WHERE password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметра запроса
                    stmt.setString(1, password);

                    // Выполнение запроса
                    ResultSet rs = stmt.executeQuery();
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int findUserByPassword(String password) {
        try {
            // Загрузка драйвера SQLite
            Class.forName("org.sqlite.JDBC");

            // Получение соединения с базой данных
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                // Подготовка SQL-запроса
                String sql = "SELECT id FROM users WHERE password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Заполнение параметра запроса
                    stmt.setString(1, password);

                    // Выполнение запроса
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getInt("id");
                        } else {
                            return -1; // Пароль не найден
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
