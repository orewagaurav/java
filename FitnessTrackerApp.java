import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FitnessTrackerApp extends JFrame {
    private JTextField idField, nameField, dateField;
    private JButton searchButton;
    private JTextArea resultArea;
    private Connection connection;

    public FitnessTrackerApp() {
        // Initialize database connection
        connectToDatabase();

        // Set up the GUI
        setTitle("Fitness Tracker Search");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Enter ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Enter Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Enter Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30)); // Reduced button size
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWorkout();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        inputPanel.add(buttonPanel);

        add(inputPanel, BorderLayout.NORTH);

        // Result Area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbProject";
            String username = "root";
            String password = "Ghd@123$gk"; // Replace with your actual password
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchWorkout() {
        String id = idField.getText();
        String name = nameField.getText();
        String date = dateField.getText();

        try {
            String query;
            PreparedStatement statement;

            if (!id.isEmpty() && !name.isEmpty() && !date.isEmpty()) {
                query = "SELECT * FROM fitness_data WHERE id = ? AND name = ? AND workout_date = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(id));
                statement.setString(2, name);
                statement.setString(3, date);
            } else if (!id.isEmpty()) {
                query = "SELECT * FROM fitness_data WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(id));
            } else if (!name.isEmpty()) {
                query = "SELECT * FROM fitness_data WHERE name = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, name);
            } else if (!date.isEmpty()) {
                query = "SELECT * FROM fitness_data WHERE workout_date = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, date);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter ID, Name, or Date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ResultSet resultSet = statement.executeQuery();
            StringBuilder result = new StringBuilder();

            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id")).append("\n");
                result.append("Name: ").append(resultSet.getString("name")).append("\n");
                result.append("Date: ").append(resultSet.getDate("workout_date")).append("\n");
                result.append("Exercise: ").append(resultSet.getString("exercise_type")).append("\n");
                result.append("Duration: ").append(resultSet.getInt("duration_minutes")).append(" mins\n");
                result.append("Calories Burned: ").append(resultSet.getInt("calories_burned")).append("\n");

                double weight = resultSet.getDouble("weight_kg");
                double height = resultSet.getDouble("height_cm") / 100; 
                double bmi = weight / (height * height);
                result.append("BMI: ").append(String.format("%.2f", bmi)).append("\n");

                if (bmi < 18.5) {
                    result.append("Health Advice: Underweight - Consider gaining weight.\n");
                } else if (bmi >= 18.5 && bmi < 25) {
                    result.append("Health Advice: Normal weight - Keep it up!\n");
                } else if (bmi >= 25 && bmi < 30) {
                    result.append("Health Advice: Overweight - Consider losing weight.\n");
                } else {
                    result.append("Health Advice: Obese - Seek medical advice.\n");
                }

                result.append("Notes: ").append(resultSet.getString("notes")).append("\n\n");
            }

            if (result.length() == 0) {
                result.append("No records found.");
            }

            resultArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FitnessTrackerApp();
    }
}

// compile: javac -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar FitnessTrackerApp.java

// run: java -cp .:/Users/gauravkumar/Downloads/mysql-connector-j-9.2.0/mysql-connector-j-9.2.0.jar FitnessTrackerApp      