module org.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires static lombok;

    opens org.example.demofx to javafx.fxml;
    exports org.example.demofx;
}