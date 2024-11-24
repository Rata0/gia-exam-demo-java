package org.example.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demofx.dao.SupplierDAO;
import org.example.demofx.model.Supplier;

import java.sql.Connection;
import java.sql.SQLException;

public class AddSupplierController {
    private DatabaseConnection databaseConnection;

    @FXML
    private TextField inputType;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputDirector;

    @FXML
    private TextField inputTelephone;

    @FXML
    private TextField inputRating;

    @FXML
    protected void addPersonClick() throws SQLException {
        databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String type = inputType.getText();
        String name = inputName.getText();
        String director = inputDirector.getText();
        String phone = inputTelephone.getText();
        int rating = Integer.parseInt(inputRating.getText());

        Supplier supplier = new Supplier(type, name, director, phone, rating);
        SupplierDAO supplierDAO = new SupplierDAO(connection);
        supplierDAO.save(supplier);

        Stage stage = (Stage) inputRating.getScene().getWindow();
        stage.close();
    }
}
