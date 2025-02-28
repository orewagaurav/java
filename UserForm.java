import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserForm extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> branchField;
    private JButton submitButton;

    // MySQL Connection Details
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Ghd@123$gk";

    public UserForm() {
        setTitle("User Registration");
        setSize(500, 400); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12); // Increased padding

        // Title Label
        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Name Label & Field
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20); // Increased text field size
        gbc.gridx = 1;
        add(nameField, gbc);

        // Email Label & Field
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Branch Label & Dropdown
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Branch:"), gbc);
        String[] branches = {"CSE", "ECE", "IT", "ME", "Civil", "Other"};
        branchField = new JComboBox<>(branches);
        branchField.setPreferredSize(new Dimension(200, 25)); // Larger dropdown
        gbc.gridx = 1;
        add(branchField, gbc);

        // Password Label & Field
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(51, 153, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setOpaque(true);
        submitButton.setPreferredSize(new Dimension(150, 40)); // Bigger button

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase(nameField.getText(), emailField.getText(), branchField.getSelectedItem().toString(), new String(passwordField.getPassword()));
            }
        });

        setVisible(true);
    }

    private void saveToDatabase(String name, String email, String branch, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO users (name, email, branch, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, branch);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data inserted successfully!");
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserForm());
    }
}

// compile - javac -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar UserForm.java

// run - java -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar UserForm
