package org.example.demofx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public void openStage(String id, String type, String name, String director, String phone, String rating) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondController.class.getResource("second.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 480, 480);
        Stage stage = new Stage();
        stage.setTitle("Second");
        stage.setScene(scene);
        SecondController secondController = fxmlLoader.getController();
        secondController.loadData(id, type, name, director, phone, rating);
        stage.show();
    }

    public void debuger(String string){
        System.out.println(string);
    }
}
