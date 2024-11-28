package org.example.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demofx.dao.SupplierDAO;
import org.example.demofx.model.Supplier;

import java.sql.Connection;
import java.sql.SQLException;

public class SecondController {
    private DatabaseConnection databaseConnection;

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

    @FXML
    protected void saveUpdate() throws SQLException {
        databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        int id = Integer.parseInt(sLabel.getText());
        String type = mbType.getText();
        String name = iName.getText();
        String director = iDirector.getText();
        String phone = iPhone.getText();
        int rating = Integer.parseInt(iRating.getText());

        Supplier supplier = new Supplier(type, name, director, phone, rating);
        SupplierDAO supplierDAO = new SupplierDAO(connection);
        supplierDAO.update(id, supplier);

        Stage stage = (Stage) sLabel.getScene().getWindow();
        stage.close();
    }
}
