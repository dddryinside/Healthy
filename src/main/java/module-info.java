module com.dddryinside.weightchecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires javafx.web;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires lombok;

    opens com.dddryinside to javafx.fxml;
    exports com.dddryinside;
    exports com.dddryinside.controllers;
    opens com.dddryinside.controllers to javafx.fxml;
    opens com.dddryinside.controllers.tests to javafx.fxml;
    exports com.dddryinside.controllers.calculators;
    opens com.dddryinside.controllers.calculators to javafx.fxml;
    exports com.dddryinside.controllers.tests;
}