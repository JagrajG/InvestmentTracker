import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DatabaseHelper {
    public static final String DB_URL = "jdbc:sqlite:investment_data.db";

    public static Connection connect() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Good!");
        } catch (SQLException e) {
            System.out.println("Connection bad!");
        }

        return conn;
    }

    public static void createTable() {
        String sql = """
               CREATE TABLE IF NOT EXISTS investments(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               metal TEXT,
               amount REAL,
               purchase_price REAL,
               price_per_oz REAL,
               spot_price REAL,
               profit_loss REAL
               );
               """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
        }
    }

    public static List<InvestmentRecord> loadAllFromDatabase() {
        List<InvestmentRecord> records = new ArrayList<>();

        String sql = "SELECT metal, amount, purchase_price, price_per_oz, spot_price, profit_loss FROM investments";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InvestmentRecord record = new InvestmentRecord(
                        rs.getString("metal"),
                        rs.getDouble("amount"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("price_per_oz"),
                        rs.getDouble("spot_price"),
                        rs.getDouble("profit_loss")
                );
                records.add(record);
            }

        } catch (SQLException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        return records;
    }
}

