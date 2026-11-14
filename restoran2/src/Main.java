import javax.swing.*;
import java.awt.*;

public class Main {

    public static int currentUserId = -1; // текущая сессия

    public static void main(String[] args) {

        JFrame f = new JFrame("Регистрация");
        f.setSize(250, 300);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(7, 1, 7, 7));

        JLabel ll = new JLabel("Логин");
        JTextField tf1 = new JTextField();

        JLabel lp = new JLabel("Пароль");
        JTextField tf2 = new JTextField();

        JButton b1 = new JButton("Зарегистрироваться");
        JButton b2 = new JButton("Войти");

        b1.addActionListener(e -> {
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(f, "Заполните логин и пароль");
                return;
            }
            boolean ok = Database.register(tf1.getText(), tf2.getText());
            JOptionPane.showMessageDialog(f, ok ? "Готово!" : "Логин занят");
        });

        b2.addActionListener(e -> {
            int id = Database.login(tf1.getText(), tf2.getText());
            if (id > 0) {
                currentUserId = id;
                f.dispose();
                new Katalog(id);
            } else {
                JOptionPane.showMessageDialog(f, "Неверный логин или пароль");
            }
        });

        p.add(ll); p.add(tf1);
        p.add(lp); p.add(tf2);
        p.add(b1); p.add(b2);

        f.add(p);
        f.setVisible(true);
    }
}