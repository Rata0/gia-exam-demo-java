module org.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires java.sql;
    requires static lombok;
    requires jdk.jsobject;

    opens org.example.demofx to javafx.fxml;
    exports org.example.demofx;
}