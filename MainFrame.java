package tables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {

    private static JPanel mainPanel, buttonsPanel, tablesPanel;

    public void init() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Таблица прямого доступа");
        setLayout(null);
        setType(Type.UTILITY);

        mainPanel = new JPanel();
        mainPanel.setBounds(10, 0, 770, 550);
        mainPanel.setLayout(new BorderLayout());
        tablesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        Algorithm.clear();
        updateTables();
        mainPanel.add(tablesPanel);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(createButton("Добавить", (ActionEvent e) -> {
            Algorithm.add(JOptionPane.showInputDialog("Введите идентификаторы через пробел"));
            updateTables();
        }));
        buttonsPanel.add(createButton("Очистить", (ActionEvent e) -> {
            Algorithm.clear();
            updateTables();
        }));
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_START);

        add(mainPanel);
        setResizable(false);
        setVisible(true);
    }

    private void updateTables() {
        tablesPanel.removeAll();
        tablesPanel.add(new JScrollPane(createTable(Algorithm.getHeadsTable(), new String[]{"ID", "HEAD"})));
        tablesPanel.add(new JScrollPane(createTable(Algorithm.getListsTable(), new String[]{"ADRESS", "ID", "NEXT"})));
        tablesPanel.revalidate();
    }

    private static JTable createTable(String[][] data, String[] titles) {
        JTable newTable = new JTable(data, titles);
        newTable.setBorder(BorderFactory.createLineBorder(Color.black));
        newTable.setEnabled(false);
        return newTable;
    }

    private static JButton createButton(String text, ActionListener al) {
        JButton newButton = new JButton(text);
        newButton.setFocusable(false);
        newButton.setBackground(new Color(0xAAEA34));
        newButton.setFont(new Font("courier new", 1, 14));
        newButton.addActionListener(al);
        return newButton;
    }
}
