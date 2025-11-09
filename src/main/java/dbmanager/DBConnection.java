package dbmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBConnection {
    private static final String DB_FOLDER = "data";
    private static final String DB_FILE = "productDB.db";
    private static final String URL = "jdbc:sqlite:" + DB_FOLDER + "/" + DB_FILE;

    public DBConnection() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Files.createDirectories(Paths.get(DB_FOLDER));
            conn = DriverManager.getConnection(URL);
            try (Statement st = conn.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON;");
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
