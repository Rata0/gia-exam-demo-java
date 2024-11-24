package org.example.demofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HelloApplication extends Application {
    private DatabaseConnection databaseConnection;

    @Override
    public void start(Stage stage) throws Exception {
        databaseConnection = new DatabaseConnection();

        createTableIfNotExists();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS suppliers ("
                + "id SERIAL PRIMARY KEY,"
                + "type VARCHAR(255),"
                + "name VARCHAR(255),"
                + "director VARCHAR(255),"
                + "phone VARCHAR(255),"
                + "rating INT);";

        try (var statement = databaseConnection.getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица suppliers успешно создана или уже существует");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
