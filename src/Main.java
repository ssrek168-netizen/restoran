import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

//регистрация и корзина
public class Main {

    private static final Set<String> login = new HashSet<>();
    private static final Set<String> password = new HashSet<>();


    public static void main (String[]args){
        //Элементы окна регистрации

        JFrame f = new JFrame();
        f.setSize(200, 300);

        f.setTitle("Регистрация");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GridLayout(7, 3, 7, 7));


        JLabel ll = new JLabel("Логин");

        JTextField tf1 = new JTextField();
        tf1.setSize(200, 300);

        JLabel lp = new JLabel("Пароль");

        JTextField tf2 = new JTextField();
        tf2.setSize(200, 300);

        JButton b1 = new JButton("Зарегистрироваться");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.add(tf1.getText());
                password.add(tf2.getText());

                for (int i = 0; i < login.size(); i++) {
                    System.out.println(i + " " + login + " " + password);
                }

            }
        });

        JButton b2 = new JButton("Войти");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //перебираем весь список
                for (int i = 0; i<login.size(); i++) {


                }

                for (String b : login) {
                    if (tf1.getText().equals(b)){
                        f.dispose();
                        new Katalog();
                        if (tf2.getText().equals(password)){
                            System.out.println("открыть окно!!!");
                        }
                    }
                    if (!tf1.getText().equals(b)){
                        System.out.println("логин не тот");
                    }

                    System.out.println(login + " " + password);
                }

            }
        });

        //Окно и панель




        f.add(p);

        p.add(ll);
        p.add(tf1);
        p.add(lp);
        p.add(tf2);
        p.add(b1);
        p.add(b2);

        f.setVisible(true);
    }
}