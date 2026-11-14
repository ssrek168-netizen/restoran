import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CartWindow {
    private final int userId;

    public CartWindow(int userId) {
        this.userId = userId;
    }

    public void show() {
        JFrame frame = new JFrame("Корзина");
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<String[]> items = Database.getCartItems(userId);

        if (items.isEmpty()) {
            panel.add(new JLabel("Корзина пуста"));
        } else {
            for (String[] row : items) {
                String name  = row[0];
                int price    = Integer.parseInt(row[1]);
                int qty      = Integer.parseInt(row[2]);
                int lineSum  = price * qty;

                JLabel itemLabel = new JLabel(
                        name + " — " + price + " руб. × " + qty +
                                " = " + lineSum + " руб.");
                itemLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.add(itemLabel);
                panel.add(Box.createVerticalStrut(5));
            }

            panel.add(Box.createVerticalStrut(15));
            JLabel total = new JLabel("Итого: " + Database.getTotal(userId) + " руб.");
            total.setFont(new Font("Arial", Font.BOLD, 16));
            total.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(total);
        }

        frame.add(new JScrollPane(panel));
        frame.setVisible(true);
    }
}