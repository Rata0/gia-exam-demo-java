package org.example.demofx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demofx.dao.SupplierDAO;
import org.example.demofx.model.Supplier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Helper {
    private DatabaseConnection databaseConnection;
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

    public String convertSuppliersToJson() throws SQLException {
        databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        SupplierDAO supplierDAO = new SupplierDAO(connection);
        List<Supplier> suppliers = supplierDAO.getEntities();

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for (int i = 0; i < suppliers.size(); i++) {
            Supplier supplier = suppliers.get(i);
            jsonBuilder.append("{")
                    .append("\"id\":").append(supplier.getId()).append(",")
                    .append("\"type\":\"").append(supplier.getType()).append("\",")
                    .append("\"name\":\"").append(supplier.getName()).append("\",")
                    .append("\"director\":\"").append(supplier.getDirector()).append("\",")
                    .append("\"phone\":\"").append(supplier.getPhone()).append("\",")
                    .append("\"rating\":").append(supplier.getRating())
                    .append("}");

            if (i < suppliers.size() - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
