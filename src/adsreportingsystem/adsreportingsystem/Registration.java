package adsreportingsystem;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Registration extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_2;
    private JComboBox<String> combo_platform;
    private JTable table_1;
    private JTextField textField_1;
    private JComboBox<String> combobox_type;

    // Database connection details
    private String jdbcUrl = "jdbc:mysql://localhost:3306/reporting";
    private String username = "root";
    private String password = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registration frame = new Registration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Registration() {
        // Create the database and table if they don't exist
        createDatabase(jdbcUrl, username, password);
        createTable(jdbcUrl, username, password);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 587);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(119, 169, 219));
        panel.setBounds(0, 0, 781, 540);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Campaign Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(50, 103, 124, 18);
        panel.add(lblNewLabel);

        JLabel lblContact = new JLabel("Platform");
        lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblContact.setBounds(48, 160, 106, 22);
        panel.add(lblContact);

        JLabel lblNewLabel_2 = new JLabel("Budget");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(50, 131, 86, 19);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Type");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2_1.setBounds(50, 188, 53, 28);
        panel.add(lblNewLabel_2_1);

        textField = new JTextField();
        textField.setBounds(162, 105, 145, 19);
        panel.add(textField);
        textField.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(162, 131, 145, 19);
        panel.add(textField_2);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonClicked();
            }
        });
        btnNewButton.setBounds(48, 318, 106, 21);
        panel.add(btnNewButton);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateButtonClicked();
            }
        });
        btnUpdate.setBounds(173, 318, 97, 21);
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonClicked();
            }
        });
        btnDelete.setBounds(48, 364, 106, 21);
        panel.add(btnDelete);

        JButton btnClear = new JButton("Compare");
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareButtonClicked();
            }
        });
        btnClear.setBounds(173, 364, 97, 21);
        panel.add(btnClear);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(356, 79, 421, 434);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 421, 119);
        panel_1.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);
        table_1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Campaign Name", "Platform", "Budget", "Type", "Result"
            }
        ));
        table_1.getColumnModel().getColumn(0).setPreferredWidth(105);
        table_1.getColumnModel().getColumn(1).setPreferredWidth(116);
        table_1.getColumnModel().getColumn(2).setPreferredWidth(106);
        table_1.getColumnModel().getColumn(3).setPreferredWidth(95);

        JLabel lblNewLabel_1 = new JLabel("Result");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(50, 226, 52, 18);
        panel.add(lblNewLabel_1);

        combo_platform = new JComboBox<>();
        combo_platform.setMaximumRowCount(2);
        combo_platform.setModel(new DefaultComboBoxModel<>(new String[] {"Google", "Meta"}));
        combo_platform.setBounds(164, 163, 143, 21);
        panel.add(combo_platform);

        combobox_type = new JComboBox<>();
        combobox_type.setModel(new DefaultComboBoxModel<>(new String[] {"Awareness", "Leadgen", "Views", "Engagement"}));
        combobox_type.setMaximumRowCount(4);
        combobox_type.setBounds(162, 194, 145, 21);
        panel.add(combobox_type);

        textField_1 = new JTextField();
        textField_1.setBounds(161, 228, 146, 19);
        panel.add(textField_1);
        textField_1.setColumns(10);

        // Refresh the table to show the initial data
        refreshTable();

        // Add a listener to update the text fields when a row is selected
        table_1.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table_1.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                String campaignName = model.getValueAt(selectedRow, 0).toString();
                String platform = model.getValueAt(selectedRow, 1).toString();
                String budget = model.getValueAt(selectedRow, 2).toString();
                String type = model.getValueAt(selectedRow, 3).toString();
                String result = model.getValueAt(selectedRow, 4).toString();

                textField.setText(campaignName);
                combo_platform.setSelectedItem(platform);
                textField_2.setText(budget);
                combobox_type.setSelectedItem(type);
                textField_1.setText(result);
            }
        });
    }

    public void addButtonClicked() {
        try (
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = con.createStatement();
        ) {
            String campaignName = textField.getText();
            String platform = combo_platform.getSelectedItem().toString();
            String budget = textField_2.getText();
            String type = combobox_type.getSelectedItem().toString();
            String result = textField_1.getText();

            String insertQuery = "INSERT INTO campaign (name, platform, budget, type, result) VALUES ('" +
                campaignName + "', '" + platform + "', '" + budget + "', '" + type + "', '" + result + "')";

            stmt.executeUpdate(insertQuery);

            JOptionPane.showMessageDialog(null, "Record added successfully!");

            // Clear the input fields
            textField.setText("");
            textField_2.setText("");
            textField_1.setText("");

            // Refresh the table to show the updated data
            refreshTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void updateButtonClicked() {
        int selectedRow = table_1.getSelectedRow();
        if (selectedRow != -1) {
            try (
                Connection con = DriverManager.getConnection(jdbcUrl, username, password);
                Statement stmt = con.createStatement();
            ) {
                DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                String campaignName = textField.getText();
                String platform = combo_platform.getSelectedItem().toString();
                String budget = textField_2.getText();
                String type = combobox_type.getSelectedItem().toString();
                String result = textField_1.getText();

                String originalCampaignName = model.getValueAt(selectedRow, 0).toString();
                String originalPlatform = model.getValueAt(selectedRow, 1).toString();
                String originalBudget = model.getValueAt(selectedRow, 2).toString();
                String originalType = model.getValueAt(selectedRow, 3).toString();
                String originalResult = model.getValueAt(selectedRow, 4).toString();

                String updateQuery = "UPDATE campaign SET ";

                if (!campaignName.isEmpty()) {
                    updateQuery += "name = '" + campaignName + "', ";
                }
                if (!platform.isEmpty()) {
                    updateQuery += "platform = '" + platform + "', ";
                }
                if (!budget.isEmpty()) {
                    updateQuery += "budget = '" + budget + "', ";
                }
                if (!type.isEmpty()) {
                    updateQuery += "type = '" + type + "', ";
                }
                if (!result.isEmpty()) {
                    updateQuery += "result = '" + result + "', ";
                }

                // Remove the trailing comma and space
                updateQuery = updateQuery.substring(0, updateQuery.length() - 2);

                updateQuery += " WHERE name = '" + originalCampaignName + "' AND platform = '" + originalPlatform +
                        "' AND budget = '" + originalBudget + "' AND type = '" + originalType +
                        "' AND result = '" + originalResult + "'";

                stmt.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Record updated successfully!");

                // Clear the input fields
                textField.setText("");
                textField_2.setText("");
                textField_1.setText("");

                // Refresh the table to show the updated data
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to update.");
        }
    }

// Delete button Function  for Deleting the data
    public void deleteButtonClicked() {
        int selectedRow = table_1.getSelectedRow();
        if (selectedRow != -1) {
            try (
                Connection con = DriverManager.getConnection(jdbcUrl, username, password);
                Statement stmt = con.createStatement();
            ) {
                DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                String campaignName = model.getValueAt(selectedRow, 0).toString();
                String platform = model.getValueAt(selectedRow, 1).toString();
                String budget = model.getValueAt(selectedRow, 2).toString();
                String type = model.getValueAt(selectedRow, 3).toString();
                String result = model.getValueAt(selectedRow, 4).toString();

                String deleteQuery = "DELETE FROM campaign WHERE name = '" + campaignName + "' AND platform = '" + platform +
                        "' AND budget = '" + budget + "' AND type = '" + type + "' AND result = '" + result + "'";

                stmt.executeUpdate(deleteQuery);

                JOptionPane.showMessageDialog(null, "Record deleted successfully!");

                // Clear the input fields
                textField.setText("");
                textField_2.setText("");
                textField_1.setText("");

                // Refresh the table to show the updated data
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }
//Compare button Function
    public void compareButtonClicked() {
        JOptionPane.showMessageDialog(null, "Compare button clicked!");
    }

    public void refreshTable() {
        try (
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = con.createStatement();
        ) {
            String selectQuery = "SELECT * FROM campaign";

            ResultSet rs = stmt.executeQuery(selectQuery);

            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0); // Clear existing data in the table

            while (rs.next()) {
                String campaignName = rs.getString("name");
                String platform = rs.getString("platform");
                String budget = rs.getString("budget");
                String type = rs.getString("type");
                String result = rs.getString("result");

                model.addRow(new Object[]{campaignName, platform, budget, type, result});
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createDatabase(String jdbcUrl, String username, String password) {
        try (
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = con.createStatement();
        ) {
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS reporting";
            stmt.executeUpdate(createDatabaseQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createTable(String jdbcUrl, String username, String password) {
        try (
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = con.createStatement();
        ) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS campaign (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "platform VARCHAR(255) NOT NULL," +
                "budget VARCHAR(255) NOT NULL," +
                "type VARCHAR(255) NOT NULL," +
                "result VARCHAR(255) NOT NULL" +
                ")";
            stmt.executeUpdate(createTableQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}