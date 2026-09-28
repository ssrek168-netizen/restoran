import javax.swing.*;
import java.awt.*;

public class Katalog {

    public Katalog(int userId) {

        JFrame f = new JFrame("Каталог");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel(null);

        // ---- ГРЕЧКА (id = 1) ----
        ImageIcon icon1 = new ImageIcon(Katalog.class.getResource("/grechka.jpg"));
        Image img1 = icon1.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        JLabel l = new JLabel(new ImageIcon(img1));
        l.setBounds(10, 10, 100, 80);

        JLabel l1 = new JLabel("Гречка с мясом");
        l1.setBounds(140, 15, 160, 20);

        JLabel l2 = new JLabel("Цена: 50 руб / 100 г");
        l2.setBounds(140, 38, 180, 20);

        JButton b = new JButton("В корзину");
        b.setBounds(140, 65, 140, 30);
        b.addActionListener(e -> {
            Database.addToCart(userId, 1);
            JOptionPane.showMessageDialog(f, "Добавлено: Гречка с мясом");
        });

        // ---- ОВСЯНКА (id = 2) ----
        ImageIcon icon2 = new ImageIcon(Katalog.class.getResource("/Ovs.jpg"));
        Image img2 = icon2.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        JLabel l3 = new JLabel(new ImageIcon(img2));
        l3.setBounds(10, 110, 100, 80);

        JLabel l4 = new JLabel("Овсянка с ягодами");
        l4.setBounds(140, 115, 160, 20);

        JLabel l5 = new JLabel("Цена: 60 руб / 100 г");
        l5.setBounds(140, 138, 180, 20);

        JButton b2 = new JButton("В корзину");
        b2.setBounds(140, 165, 140, 30);
        b2.addActionListener(e -> {
            Database.addToCart(userId, 2);
            JOptionPane.showMessageDialog(f, "Добавлено: Овсянка с ягодами");
        });

        // ---- КОРЗИНА ----
        JButton openCart = new JButton("Открыть корзину");
        openCart.setBounds(140, 220, 200, 35);
        openCart.addActionListener(e -> new CartWindow(userId).show());

        f.add(p);
        p.add(l);   p.add(l1);  p.add(l2);  p.add(b);
        p.add(l3);  p.add(l4);  p.add(l5);  p.add(b2);
        p.add(openCart);

        f.setVisible(true);
    }
}