import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Main class to run the application
public class HealthCareSystemapp {
    public static void main(String[] args) {
        // Launch the main dashboard
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }
}

// Model classes
class Patient {
    private String name;
    private int age;
    private String gender;
    private String healthIssue;

    public Patient(String name, int age, String gender, String healthIssue) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.healthIssue = healthIssue;
    }

    public String getName() {
        return name;
    }

    public String getHealthIssue() {
        return healthIssue;
    }

    // Getters and Setters
}

// Backend Service Classes
class PatientService {
    private List<Patient> patients = new ArrayList<>();

    public void registerPatient(String name, int age, String gender, String healthIssue) {
        Patient patient = new Patient(name, age, gender, healthIssue);
        patients.add(patient);
        System.out.println("Patient " + name + " registered successfully.");
    }

    public Patient getPatientDetails(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }
}

// Dashboard Interface (Frontend)
class Dashboard extends JFrame {
    private PatientService patientService = new PatientService();

    public Dashboard() {
        setTitle("Community Health Care System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating a main panel with grid layout
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Add navigation buttons
        JButton registerButton = new JButton("Register Patient");
        JButton checkupButton = new JButton("Health Checkup");
        JButton historyButton = new JButton("Medical History");
        JButton appointmentsButton = new JButton("Appointments");
        JButton feedbackButton = new JButton("Feedback");
        JButton reportButton = new JButton("Generate Health Report");

        // Add action listeners for each button to navigate to different modules
        registerButton.addActionListener(e -> new RegistrationWindow(patientService));
        checkupButton.addActionListener(e -> new HealthCheckupWindow());
        historyButton.addActionListener(e -> new MedicalHistoryWindow());
        appointmentsButton.addActionListener(e -> new AppointmentsWindow());
        feedbackButton.addActionListener(e -> new FeedbackWindow());
        reportButton.addActionListener(e -> new HealthReportWindow(patientService));

        // Adding buttons to main panel
        mainPanel.add(registerButton);
        mainPanel.add(checkupButton);
        mainPanel.add(historyButton);
        mainPanel.add(appointmentsButton);
        mainPanel.add(feedbackButton);
        mainPanel.add(reportButton);

        add(mainPanel);
    }
}

// Registration Window (Frontend)
class RegistrationWindow extends JFrame {
    private JTextField nameField, ageField;
    private JComboBox<String> genderBox, issueBox;
    private PatientService patientService;

    public RegistrationWindow(PatientService patientService) {
        this.patientService = patientService;

        setTitle("Patient Registration");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(genderBox);

        panel.add(new JLabel("Health Issues:"));
        issueBox = new JComboBox<>(new String[]{"None", "Diabetes", "Hypertension", "Asthma", "Heart Issues"});
        panel.add(issueBox);

        JButton submitButton = new JButton("Register");
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            // Capture inputs
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderBox.getSelectedItem();
            String healthIssue = (String) issueBox.getSelectedItem();

            // Register the patient using the backend service
            patientService.registerPatient(name, age, gender, healthIssue);

            // Show confirmation message with the patient's input details
            String patientDetails = "Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nHealth Issue: " + healthIssue;
            JOptionPane.showMessageDialog(this, "Registration Complete!\n\n" + patientDetails, "Patient Registered", JOptionPane.INFORMATION_MESSAGE);
        });

        add(panel);
        setVisible(true);
    }
}

// Health Checkup Window (Frontend)
class HealthCheckupWindow extends JFrame {
    public HealthCheckupWindow() {
        setTitle("Health Checkup");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Symptoms:"));
        JTextField symptomsField = new JTextField();
        panel.add(symptomsField);

        panel.add(new JLabel("Duration of Symptoms:"));
        JTextField durationField = new JTextField();
        panel.add(durationField);

        JButton checkupButton = new JButton("Start Checkup");
        panel.add(checkupButton);

        checkupButton.addActionListener(e -> {
            // Capture symptoms and duration
            String symptoms = symptomsField.getText();
            String duration = durationField.getText();

            // Show confirmation with entered details
            String checkupDetails = "Symptoms: " + symptoms + "\nDuration: " + duration;
            JOptionPane.showMessageDialog(this, "Health Checkup Started!\n\n" + checkupDetails, "Checkup Details", JOptionPane.INFORMATION_MESSAGE);
        });

        add(panel);
        setVisible(true);
    }
}

// Medical History Window (Frontend)
class MedicalHistoryWindow extends JFrame {
    public MedicalHistoryWindow() {
        setTitle("Medical History");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Displaying Medical History..."));
        
        // Placeholder for future functionality.
        
        add(panel);
        setVisible(true);
    }
}

// Appointments Window (Frontend)
class AppointmentsWindow extends JFrame {
    public AppointmentsWindow() {
        setTitle("Appointments");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel dateLabel = new JLabel("Date (DD/MM/YYYY):");
        JTextField dateField = new JTextField();
        panel.add(dateLabel);
        panel.add(dateField);

        JLabel timeLabel = new JLabel("Time (HH:MM):");
        JTextField timeField = new JTextField();
        panel.add(timeLabel);
        panel.add(timeField);

        JLabel doctorLabel = new JLabel("Doctor's Name:");
        JTextField doctorField = new JTextField();
        panel.add(doctorLabel);
        panel.add(doctorField);

        JButton scheduleButton = new JButton("Schedule Appointment");
        panel.add(scheduleButton);

        scheduleButton.addActionListener(e -> {
            // Capture appointment details
            String date = dateField.getText();
            String time = timeField.getText();
            String doctor = doctorField.getText();

            // Show appointment confirmation
            String appointmentDetails = "Date: " + date + "\nTime: " + time + "\nDoctor: " + doctor;
            JOptionPane.showMessageDialog(this, "Appointment Scheduled!\n\n" + appointmentDetails, "Appointment Details", JOptionPane.INFORMATION_MESSAGE);
        });

        add(panel);
        setVisible(true);
    }
}

// Feedback Window (Frontend)
class FeedbackWindow extends JFrame {
    public FeedbackWindow() {
        setTitle("Feedback");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        panel.add(new JLabel("Provide your Feedback:"));
        JTextArea feedbackArea = new JTextArea(5, 20);
        panel.add(new JScrollPane(feedbackArea));

        JButton submitButton = new JButton("Submit Feedback");
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            // Capture feedback
            String feedback = feedbackArea.getText();

            // Show feedback confirmation
            JOptionPane.showMessageDialog(this, "Feedback Submitted!\n\nYour Feedback: " + feedback, "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
        });

        add(panel);
        setVisible(true);
    }
}

// Health Report Window (Frontend)
class HealthReportWindow extends JFrame {
    private PatientService patientService;

    public HealthReportWindow(PatientService patientService) {
        this.patientService = patientService;

        setTitle("Generate Health Report");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Enter Patient Name:");
        JTextField nameField = new JTextField(15);
        panel.add(nameLabel);
        panel.add(nameField);

        JButton generateButton = new JButton("Generate Report");
        panel.add(generateButton);

        JTextArea reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        panel.add(new JScrollPane(reportArea));

        generateButton.addActionListener(e -> {
            String name = nameField.getText();
            Patient patient = patientService.getPatientDetails(name);

            if (patient != null) {
                // Generate the health report based on patient details
                String report = generateHealthReport(patient);
                reportArea.setText(report);
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
        setVisible(true);
    }

    private String generateHealthReport(Patient patient) {
        Random rand = new Random();
        String[] conditions = {"Healthy", "Mild fever", "Cold & Cough", "High Blood Pressure", "Diabetes"};
        return "Health Status: " + conditions[rand.nextInt(conditions.length)] + "\n" +
                "Last Checkup Date: " + (rand.nextInt(12) + 1) + "/" + (rand.nextInt(12) + 1) + "/2024" + "\n" +
                "Patient: " + patient.getName() + "\n" +
                "Health Issue: " + patient.getHealthIssue();
    }
}
