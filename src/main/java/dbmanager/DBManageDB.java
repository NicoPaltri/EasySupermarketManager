package dbmanager;

import easysupermarket.ProductTypology;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManageDB {

    public static void insertProduct(int id, String name, double pricePerUnit, ProductTypology productTypology) {
        String sql = "INSERT INTO products (id, name, pricePerUnit, typology) VALUES (?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, pricePerUnit);
            ps.setString(4, productTypology.name());

            ps.executeUpdate();

            System.out.printf("Product inserted: [id=%d, name=%s, pricePerUnit=%.2f, typology=%s]%n",
                    id, name, pricePerUnit, productTypology.name());
        } catch (SQLException e) {
            System.err.println("Error inserting product (query: " + sql + "): " + e.getMessage());
        }
    }

    public static boolean deleteProductById(int id) {
        String sql = "DELETE FROM products WHERE id = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product with ID " + id + " removed successfully.");
                return true;
            } else {
                System.out.println("No product found with ID " + id + ".");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error deleting product (query: " + sql + "): " + e.getMessage());
            return false;
        }
    }
}
