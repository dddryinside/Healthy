package com.dddryinside.service;

import com.dddryinside.models.Note;
import com.dddryinside.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseAccess {
    private static final String DB_URL = "jdbc:sqlite:./mental.db";
    public static List<Note> getNotes(int amount) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            isDiaryTableExists();

            String insertQuery = "SELECT id, content, date FROM diary WHERE user_id = ? LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, SecurityManager.getUser().getId());
            statement.setInt(2, amount);
            ResultSet resultSet = statement.executeQuery();

            List<Note> notes = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                LocalDate date = LocalDate.parse(resultSet.getString("date"));

                Note diaryNote = new Note(id, SecurityManager.getUser(), content, date);
                notes.add(diaryNote);
            }

            Collections.reverse(notes);
            return notes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void isDiaryTableExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            if (!tableExists(connection)) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS diary (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "user_id INTEGER," +
                        "content TEXT," +
                        "date DATETIME)";
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
        ResultSet resultSet = metadata.getTables(null, null, "diary", null);
        return resultSet.next();
    }

}
