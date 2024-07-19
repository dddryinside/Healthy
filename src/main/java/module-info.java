module com.dddryinside.weightchecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires javafx.web;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires lombok;
    requires org.apache.commons.lang3;
    requires org.fxmisc.richtext;

    opens com.dddryinside to javafx.fxml;
    exports com.dddryinside;
}