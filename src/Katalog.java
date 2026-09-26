import javax.swing.*;
import java.awt.*;

public class Katalog {

    public Katalog() {
        // Создаём корзину
        Cart cart = new Cart();

        // Создаём товары
        Product grechka = new Product("Гречка с мясом", 50);
        Product ovsyanka = new Product("Овсянка с ягодами", 60);

        JFrame f = new JFrame();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel(null);

        // --- ГРЕЧКА ---
        ImageIcon originalIcon = new ImageIcon(Katalog.class.getResource("/grechka.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        JLabel l = new JLabel(new ImageIcon(scaledImage));
        l.setBounds(10, 10, 100, 80);

        JLabel l1 = new JLabel("Гречка с мясом");
        l1.setBounds(140, 15, 160, 20);

        JLabel l2 = new JLabel("Цена: 50 руб / 100 г");
        l2.setBounds(140, 38, 180, 20);

        JButton b = new JButton("В корзину");
        b.setBounds(140, 65, 140, 30);
        b.addActionListener(e -> {
            cart.add(grechka);
            JOptionPane.showMessageDialog(f, "Добавлено: " + grechka.getName());
        });

        // --- ОВСЯНКА ---
        ImageIcon originalIcon1 = new ImageIcon(Katalog.class.getResource("/Ovs.jpg"));
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        JLabel l3 = new JLabel(new ImageIcon(scaledImage1));
        l3.setBounds(10, 110, 100, 80);

        JLabel l4 = new JLabel("Овсянка с ягодами");
        l4.setBounds(140, 115, 160, 20);

        JLabel l5 = new JLabel("Цена: 60 руб / 100 г");
        l5.setBounds(140, 138, 180, 20);

        JButton b2 = new JButton("В корзину");
        b2.setBounds(140, 165, 140, 30);
        b2.addActionListener(e -> {
            cart.add(ovsyanka);
            JOptionPane.showMessageDialog(f, "Добавлено: " + ovsyanka.getName());
        });

        // --- Кнопка "Открыть корзину" ---
        JButton openCart = new JButton("Открыть корзину");
        openCart.setBounds(140, 220, 200, 35);
        openCart.addActionListener(e -> new CartWindow(cart).show());

        // Добавление
        f.add(p);
        p.add(l);   p.add(l1);  p.add(l2);  p.add(b);
        p.add(l3);  p.add(l4);  p.add(l5);  p.add(b2);
        p.add(openCart);

        f.setVisible(true);
    }
}