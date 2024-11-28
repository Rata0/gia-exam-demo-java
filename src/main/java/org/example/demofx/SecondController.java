package org.example.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class SecondController {
    @FXML
    private Label sLabel;

    @FXML
    private MenuButton mbType;

    @FXML
    private TextField iName;

    @FXML
    private TextField iDirector;

    @FXML
    private TextField iPhone;

    @FXML
    private TextField iRating;

    @FXML
    private MenuItem menuItem3;

    protected void loadData(String id, String type, String name, String director, String phone, String rating) {
        sLabel.setText(id);
        mbType.setText(type);
        iName.setText(name);
        iDirector.setText(director);
        iPhone.setText(phone);
        iRating.setText(rating);
    }

    @FXML
    protected void initialize() {
        MenuItem menuItem1 = new MenuItem("ООО");
        MenuItem menuItem2 = new MenuItem("ЗАО");

        mbType.getItems().addAll(menuItem1, menuItem2);

        menuItem1.setOnAction((e) -> {
            mbType.setText(menuItem1.getText());
        });

        menuItem2.setOnAction((e) -> {
            mbType.setText(menuItem2.getText());
        });

        menuItem3.setOnAction((e) -> {
            mbType.setText(menuItem3.getText());
        });
    }
}
