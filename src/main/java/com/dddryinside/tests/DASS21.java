package com.dddryinside.tests;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dddryinside.service.AllTests;
import com.dddryinside.service.Patient;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dddryinside.service.DataBaseAccess.DB_URL;

public class DASS21 implements Test {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class DTO {
        private LocalDate date;
        private int depression;
        private int anxiety;
        private int stress;
    }

    @Override
    public LineChart<String, Number> showStatistics(Patient patient) {
        // Создание осей
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // Создание LineChart
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle(AllTests.DASS21.getFullName());

        // Настройка осей
        yAxis.setLabel("Значения");
        yAxis.setTickUnit(1.0); // Установка шага равным 1
        yAxis.setMinorTickVisible(false); // Скрытие малых делений на оси
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%d", object.intValue());
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        }); // Форматирование меток на оси Y как целые числа


        List<DASS21.DTO> dass21List = getTestResults(patient);
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Депрессия");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Тревожность");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Стресс");
        for (int i = 0; i < dass21List.size(); i++) {
            DASS21.DTO test = dass21List.get(i);
            series1.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")" , test.getDepression()));
            series2.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")", test.getAnxiety()));
            series3.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")", test.getStress()));
        }

        // Добавление серий в LineChart
        lineChart.getData().addAll(series1, series2, series3);

        return lineChart;
    }

    @Override
    public Parent initializeUI() {
        return new VBox();
    }

    private static void checkTestTableExist() {

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

    public static void saveTestResult(int patient_id, int depression, int anxiety, int stress) {
        checkTestTableExist();

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

    private List<DTO> getTestResults(Patient patient) {
        checkTestTableExist();
        List<DTO> testResultsList = new ArrayList<>();

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

                    DTO result = new DTO(date, depression, anxiety, stress);
                    testResultsList.add(result);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Более подробная обработка ошибок
            e.printStackTrace();
        }

        return testResultsList;
    }
}
