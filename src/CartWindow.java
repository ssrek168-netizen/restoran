import javax.swing.*;
import java.awt.*;

public class CartWindow {
    private Cart cart;

    public CartWindow(Cart cart) {
        this.cart = cart;
    }

    public void show() {
        JFrame frame = new JFrame("Корзина");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null); // по центру экрана

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        if (cart.getItems().isEmpty()) {
            panel.add(new JLabel("Корзина пуста"));
        } else {
            for (Product p : cart.getItems()) {
                JLabel itemLabel = new JLabel(p.toString());
                itemLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.add(itemLabel);
                panel.add(Box.createVerticalStrut(5));
            }
            panel.add(Box.createVerticalStrut(15));

            JLabel total = new JLabel("Итого: " + cart.getTotalPrice() + " руб.");
            total.setFont(new Font("Arial", Font.BOLD, 16));
            total.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(total);
        }

        frame.add(new JScrollPane(panel));
        frame.setVisible(true);
    }
}