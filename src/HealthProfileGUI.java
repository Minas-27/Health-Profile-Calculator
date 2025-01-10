import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class HealthProfileGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Health Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new BorderLayout());

        // Add a title label with custom styling
        JLabel titleLabel = new JLabel("Health Profile Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel Blue
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(245, 245, 245)); // Light Gray

        // Input fields and labels
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderDropdown = new JComboBox<>(new String[]{"Male", "Female"});

        JLabel birthMonthLabel = new JLabel("Birth Month (1-12):");
        JTextField birthMonthField = new JTextField();

        JLabel birthDayLabel = new JLabel("Birth Day (1-31):");
        JTextField birthDayField = new JTextField();

        JLabel birthYearLabel = new JLabel("Birth Year:");
        JTextField birthYearField = new JTextField();

        JLabel heightLabel = new JLabel("Height (in cm):");
        JTextField heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight (in kg):");
        JTextField weightField = new JTextField();

        // Add components to the input panel
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderDropdown);
        inputPanel.add(birthMonthLabel);
        inputPanel.add(birthMonthField);
        inputPanel.add(birthDayLabel);
        inputPanel.add(birthDayField);
        inputPanel.add(birthYearLabel);
        inputPanel.add(birthYearField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton calculateButton = new JButton("Calculate Health Profile");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        buttonPanel.add(calculateButton);

        // Output area
        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        outputArea.setBackground(new Color(240, 248, 255)); // Alice Blue

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Health Profile Details"));
        scrollPane.setBackground(new Color(245, 245, 245)); // Light Gray

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input values
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String gender = (String) genderDropdown.getSelectedItem();
                    int birthMonth = Integer.parseInt(birthMonthField.getText().trim());
                    int birthDay = Integer.parseInt(birthDayField.getText().trim());
                    int birthYear = Integer.parseInt(birthYearField.getText().trim());
                    double heightCm = Double.parseDouble(heightField.getText().trim());
                    double weightKg = Double.parseDouble(weightField.getText().trim());

                    // Validate inputs
                    if (birthMonth < 1 || birthMonth > 12 || birthDay < 1 || birthDay > 31 || birthYear < 1900 || birthYear > LocalDate.now().getYear()) {
                        throw new IllegalArgumentException("Invalid date of birth entered.");
                    }

                    // Convert height to meters
                    double heightMeters = heightCm / 100;

                    // Calculate age, BMI, max heart rate, and target heart rate range
                    LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
                    int age = Period.between(birthDate, LocalDate.now()).getYears();
                    double bmi = weightKg / (heightMeters * heightMeters);
                    int maxHeartRate = 220 - age;
                    String targetHeartRateRange = String.format("%d - %d bpm", (int) (0.5 * maxHeartRate), (int) (0.85 * maxHeartRate));

                    // Display results
                    StringBuilder result = new StringBuilder();
                    result.append("Health Profile:\n");
                    result.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
                    result.append("Gender: ").append(gender).append("\n");
                    result.append("Date of Birth: ").append(birthMonth).append("/").append(birthDay).append("/").append(birthYear).append("\n");
                    result.append("Height: ").append(heightCm).append(" cm\n");
                    result.append("Weight: ").append(weightKg).append(" kg\n");
                    result.append("Age: ").append(age).append(" years\n");
                    result.append(String.format("BMI: %.2f\n", bmi));
                    result.append("Maximum Heart Rate: ").append(maxHeartRate).append(" bpm\n");
                    result.append("Target Heart Rate Range: ").append(targetHeartRateRange).append("\n\n");
                    result.append("BMI VALUES:\n");
                    result.append("Underweight: less than 18.5\n");
                    result.append("Normal:      18.5 - 24.9\n");
                    result.append("Overweight:  25 - 29.9\n");
                    result.append("Obese:       30 or greater\n");

                    outputArea.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for height, weight, and date of birth.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.EAST);

        // Make the frame visible
        frame.setVisible(true);
    }
}
