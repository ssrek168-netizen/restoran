import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:C:/sqlite/shop.db";
        try (Connection c = DriverManager.getConnection(url)) {
            System.out.println("Подключено: SQLite " + c.getMetaData().getDatabaseProductVersion());

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT name, price FROM products");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " — " + rs.getInt("price"));
            }
        }
    }
}