package org.example.demofx.dao;

import org.example.demofx.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class SupplierDAO {
    private Connection connection;

    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers (type, name, director, phone, rating) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, supplier.getType());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getDirector());
            preparedStatement.setString(4, supplier.getPhone());
            preparedStatement.setInt(5, supplier.getRating());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting supplier failed, no rows affected.");
            }

            try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    supplier.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("DB has not returned an id after saving an entity");
                }
            }
        }
    }

    public List<Supplier> getEntities() throws SQLException {
        String sql = "SELECT * FROM suppliers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Supplier>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String director = resultSet.getString("director");
                String phone = resultSet.getString("phone");
                int rating = resultSet.getInt("rating");


                Supplier supplier= new Supplier(type, name, director, phone, rating);
                supplier.setId(id);
                result.add(supplier);
            }
            return result;
        }
    }

    public Supplier findById(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String type = resultSet.getString("type");
                    String name = resultSet.getString("name");
                    String director = resultSet.getString("director");
                    String phone = resultSet.getString("phone");
                    int rating = resultSet.getInt("rating");

                    Supplier supplier = new Supplier(type, name, director, phone, rating);
                    supplier.setId(id);
                    return supplier;
                } else {
                    return null;
                }
            }
        }
    }
}
