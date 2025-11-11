package dbmanager;

import easysupermarket.ProductTypology;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManageDB {
    public void insertProduct(int ID, String name, double pricePerUnit, ProductTypology productTypology) {
        String sql = "INSERT INTO products (id, name, pricePerUnit, typology) VALUES (?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ID);
            ps.setString(2, name);
            ps.setDouble(3, pricePerUnit);
            ps.setString(4, productTypology.name());

            ps.executeUpdate();

            System.out.println("Product inserted. " +
                    "id: " + ID +
                    ", name: " + name +
                    ", pricePerUnit: " + pricePerUnit +
                    ", typology: " + productTypology);
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
        }
    }

    public void deleteProductById(int id) {
        String sql = "DELETE FROM products WHERE id = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product with ID " + id + " removed successfully.");
            } else {
                System.out.println("No product found with ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }

}
