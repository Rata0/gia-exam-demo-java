package org.example.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.demofx.dao.SupplierDAO;
import org.example.demofx.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Hello");
        String url = "jdbc:postgresql://localhost:5432/partners";
        String user = "postgres";
        String password = "12345";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            var sql = "CREATE TABLE IF NOT EXISTS suppliers ("
                    + "id SERIAL PRIMARY KEY,"
                    + "type VARCHAR(255),"
                    + "name VARCHAR(255),"
                    + "director VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    + "rating INT);";

            try (var statement = connection.createStatement()) {
                statement.execute(sql);
            }

            SupplierDAO supplierDAO = new SupplierDAO(connection);
            Supplier supplier = new Supplier("2", "1", "4", "1", 1);
            Supplier supplier2 = new Supplier("2", "1", "1", "2", 1);
            Supplier supplier3 = new Supplier("2", "1", "1", "1", 1);

            supplierDAO.save(supplier);
            supplierDAO.save(supplier2);
            supplierDAO.save(supplier3);

            System.out.println(supplierDAO.getEntities());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}