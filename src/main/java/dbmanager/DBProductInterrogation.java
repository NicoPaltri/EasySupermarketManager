package dbmanager;

import easysupermarket.ProductTypology;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBProductInterrogation implements ProductInterrogation {
    public boolean doesMyProductExists(int id) {
        String sql = "SELECT 1 FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error checking existence of id=" + id, e);
        }
    }

    public ProductTypology getTypologyFromSource(int id) {
        String sql = "SELECT typology FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return ProductTypology.valueOf(rs.getString("typology"));
                } else {
                    throw new IllegalArgumentException("Unknown product id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error reading typology for id=" + id, e);
        }
    }

    public String getNameFromSource(int id) {
        String sql = "SELECT name FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    throw new IllegalArgumentException("Unknown product id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error reading name for id=" + id, e);
        }
    }

    public double getPricePerUnitFromSource(int id) {
        String sql = "SELECT pricePerUnit FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("pricePerUnit");
                } else {
                    throw new IllegalArgumentException("Unknown product id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error reading pricePerUnit for id=" + id, e);
        }
    }
}
