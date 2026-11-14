import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Table extends JFrame {

    public Table() {

        setTitle("Пользователи БД");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        String[] columnNames = {"ID", "Название", "Цена"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<String[]> products = Database.getAllProducts();
        for (String[] productsRow : products) {
            tableModel.addRow(productsRow);
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(25);

        add(panel);
        panel.add(new JScrollPane(table) , gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Table());
    }
}