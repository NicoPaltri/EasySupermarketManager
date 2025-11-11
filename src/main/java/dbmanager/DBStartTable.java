package dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStartTable {
    private DBStartTable() {}

    public static void ensureCreated() throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS products(
                  id            INTEGER PRIMARY KEY,
                  name          TEXT NOT NULL,
                  pricePerUnit  REAL NOT NULL,
                  typology      TEXT NOT NULL
                    CHECK(typology IN ('UNIT_PRODUCT','WEIGHTED_PRODUCT'))
                );
            """);

            st.execute("CREATE INDEX IF NOT EXISTS idx_products_typology ON products(typology);");
        }
    }
}
