package org.example.demofx;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    protected void onHelloButtonClick() {
        try {
            Stage stage = new Stage();
            new AddSupplier().start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}