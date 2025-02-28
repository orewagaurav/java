import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserForm extends JFrame {
    private JTextField nameField, emailField;
    private JButton submitButton;

    // MySQL Connection Details
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";  
    private static final String PASSWORD = "Ghd@123$gk";  

    public UserForm() {
        setTitle("User Registration");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        
        // Title Label
        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Name Label
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);

        // Name Field
        nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        add(nameField, gbc);

        // Email Label
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);

        // Email Field
        emailField = new JTextField(15);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        add(emailField, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(51, 153, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setOpaque(true);
        
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase(nameField.getText(), emailField.getText());
            }
        });

        setVisible(true);
    }

    private void saveToDatabase(String name, String email) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            
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

// // compile - javac -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar UserForm.java

// // run - java -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar UserForm
