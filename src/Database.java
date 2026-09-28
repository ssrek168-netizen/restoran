import java.sql.*;

public class Database {
    // Путь к файлу базы. ВАЖНО: в Java используйте ПРЯМЫЕ слэши /
    private static final String DB_PATH = "C:/sqlite/shop.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // ---------- ПОЛЬЗОВАТЕЛИ ----------

    public static boolean register(String login, String password) {
        String sql = "INSERT INTO users(login, password) VALUES(?, ?)";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Регистрация не удалась: " + e.getMessage());
            return false;
        }
    }

    /** @return id пользователя или -1, если логин/пароль не совпали */
    public static int login(String login, String password) {
        String sql = "SELECT id FROM users WHERE login = ? AND password = ?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // ---------- КОРЗИНА ----------
    // В SQLite нет хранимых процедур — логика та же, но в PreparedStatement

    public static void addToCart(int userId, int productId) {
        // 1. Проверяем, есть ли такой товар у пользователя
        String check = "SELECT id FROM cart_items WHERE user_id = ? AND product_id = ?";
        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(check)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // товар уже есть — увеличиваем количество
                int id = rs.getInt("id");
                try (PreparedStatement up = c.prepareStatement(
                        "UPDATE cart_items SET quantity = quantity + 1 WHERE id = ?")) {
                    up.setInt(1, id);
                    up.executeUpdate();
                }
            } else {
                // товара нет — добавляем
                try (PreparedStatement ins = c.prepareStatement(
                        "INSERT INTO cart_items(user_id, product_id, quantity) VALUES(?, ?, 1)")) {
                    ins.setInt(1, userId);
                    ins.setInt(2, productId);
                    ins.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Возвращает строки корзины с названием, ценой и количеством */
    public static java.util.List<String[]> getCartItems(int userId) {
        java.util.List<String[]> result = new java.util.ArrayList<>();
        String sql = """
            SELECT p.name, p.price, c.quantity
            FROM cart_items c
            JOIN products p ON p.id = c.product_id
            WHERE c.user_id = ?
            ORDER BY c.id
        """;
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new String[] {
                        rs.getString("name"),
                        String.valueOf(rs.getInt("price")),
                        String.valueOf(rs.getInt("quantity"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** Общая стоимость корзины */
    public static int getTotal(int userId) {
        String sql = """
            SELECT COALESCE(SUM(p.price * c.quantity), 0) AS total
            FROM cart_items c
            JOIN products p ON p.id = c.product_id
            WHERE c.user_id = ?
        """;
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void clearCart(int userId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}