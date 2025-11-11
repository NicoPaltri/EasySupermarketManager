package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStartTable {
    public DBStartTable() {
    }

    public static void ensureCreated(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {

            st.execute("""
                        CREATE TABLE IF NOT EXISTS products(
                          id          INTEGER PRIMARY KEY,
                          name        TEXT NOT NULL,
                          pricePerUnit  REAL NOT NULL,
                          typology        TEXT NOT NULL CHECK(typology IN ('UNIT_PRODUCT','WEIGHTED_PRODUCT')),
                        );
                    """);

            st.execute("CREATE INDEX IF NOT EXISTS idx_products_type ON products(type);");
        }
    }
}
