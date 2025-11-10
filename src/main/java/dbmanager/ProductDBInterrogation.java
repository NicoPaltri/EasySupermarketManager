package dbmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDBInterrogation implements ProductInterrogation {
    public boolean doMyProductExists(int ID) {
        String sql = "SELECT 1 FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error checking existence of id=" + ID, e);
        }
    }

    public String getTypologyFromDB(int ID) {
        String sql = "SELECT typology FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("typology");
                } else {
                    throw new IllegalArgumentException("Given an ID not checked before");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error checking existence of id=" + ID, e);
        }
    }

    public String getNameFromDB(int ID) {
        String sql = "SELECT name FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    throw new IllegalArgumentException("Given an ID not checked before");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error checking existence of id=" + ID, e);
        }
    }

    public double getPricePerUnitFromDB(int ID) {
        String sql = "SELECT pricePerUnit FROM products WHERE id = ? LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("pricePerUnit");
                } else {
                    throw new IllegalArgumentException("Given an ID not checked before");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB error checking existence of id=" + ID, e);
        }
    }
}
