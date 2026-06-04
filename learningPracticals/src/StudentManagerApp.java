import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagerApp extends JFrame { 

    // --- Data Models ---
    static class Course {
        String code;
        String name;

        public Course(String code, String name) {
            this.code = code;
            this.name = name;
        }
        @Override
        public String toString() { return code + " - " + name; }
    }

    static class Student {
        String id;
        String name;
        ArrayList<Course> enrolledCourses = new ArrayList<>();
        HashMap<Course, String> grades = new HashMap<>();

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public String toString() { return name + " (" + id + ")"; }
    }

    // --- Global Data Storage ---
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();

    // --- GUI Components ---
    private DefaultTableModel studentTableModel;
    private JComboBox<Student> enrollStudentCombo, gradeStudentCombo;
    private JComboBox<Course> enrollCourseCombo, gradeCourseCombo;

    public StudentManagerApp() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Pre-populate some courses
        courses.add(new Course("CS101", "Intro to Programming"));
        courses.add(new Course("MATH201", "Calculus I"));
        courses.add(new Course("ENG101", "English Literature"));

        // Main Layout: Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Management", createStudentTab());
        tabbedPane.addTab("Course Enrollment", createEnrollmentTab());
        tabbedPane.addTab("Grade Management", createGradeTab());

        add(tabbedPane);
    }

    // --- 1. Student Management Tab ---
    private JPanel createStudentTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input Form (Top)
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(15);
        JButton addButton = new JButton("Add Student");

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(addButton);

        // Data Table (Center)
        String[] columns = {"ID", "Name", "Enrolled Courses", "Grades"};
        studentTableModel = new DefaultTableModel(columns, 0);
        JTable studentTable = new JTable(studentTableModel);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Event Handler: Add Student
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Student newStudent = new Student(id, name);
            students.add(newStudent);
            idField.setText("");
            nameField.setText("");
            refreshAllViews();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        });

        return panel;
    }

    // --- 2. Course Enrollment Tab ---
    private JPanel createEnrollmentTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        enrollStudentCombo = new JComboBox<>();
        enrollCourseCombo = new JComboBox<>(courses.toArray(new Course[0]));
        JButton enrollButton = new JButton("Enroll Student");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Select Student:"), gbc);
        gbc.gridx = 1; panel.add(enrollStudentCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Select Course:"), gbc);
        gbc.gridx = 1; panel.add(enrollCourseCombo, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; panel.add(enrollButton, gbc);

        // Event Handler: Enroll
        enrollButton.addActionListener(e -> {
            Student student = (Student) enrollStudentCombo.getSelectedItem();
            Course course = (Course) enrollCourseCombo.getSelectedItem();

            if (student == null || course == null) {
                JOptionPane.showMessageDialog(this, "Please select both a student and a course.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (student.enrolledCourses.contains(course)) {
                JOptionPane.showMessageDialog(this, "Student is already enrolled in this course.", "Enrollment Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            student.enrolledCourses.add(course);
            refreshAllViews();
            JOptionPane.showMessageDialog(this, "Enrollment successful!");
        });

        return panel;
    }

    // --- 3. Grade Management Tab ---
    private JPanel createGradeTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gradeStudentCombo = new JComboBox<>();
        gradeCourseCombo = new JComboBox<>();
        JTextField gradeField = new JTextField(5);
        JButton assignButton = new JButton("Assign Grade");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Select Student:"), gbc);
        gbc.gridx = 1; panel.add(gradeStudentCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Select Enrolled Course:"), gbc);
        gbc.gridx = 1; panel.add(gradeCourseCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Grade:"), gbc);
        gbc.gridx = 1; panel.add(gradeField, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3; panel.add(assignButton, gbc);

        // Dynamic Update: When student is selected, update available courses
        gradeStudentCombo.addActionListener(e -> {
            Student selected = (Student) gradeStudentCombo.getSelectedItem();
            gradeCourseCombo.removeAllItems();
            if (selected != null) {
                for (Course c : selected.enrolledCourses) {
                    gradeCourseCombo.addItem(c);
                }
            }
        });

        // Event Handler: Assign Grade
        assignButton.addActionListener(e -> {
            Student student = (Student) gradeStudentCombo.getSelectedItem();
            Course course = (Course) gradeCourseCombo.getSelectedItem();
            String grade = gradeField.getText().trim();

            if (student == null || course == null || grade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            student.grades.put(course, grade);
            gradeField.setText("");
            refreshAllViews();
            JOptionPane.showMessageDialog(this, "Grade assigned successfully!");
        });

        return panel;
    }

    // --- Helper: Dynamic Interface Updates ---
    private void refreshAllViews() {
        // Refresh Table
        studentTableModel.setRowCount(0); // Clear existing
        for (Student s : students) {
            StringBuilder coursesStr = new StringBuilder();
            StringBuilder gradesStr = new StringBuilder();
            
            for (Course c : s.enrolledCourses) {
                coursesStr.append(c.code).append(", ");
                String grade = s.grades.getOrDefault(c, "N/A");
                gradesStr.append(c.code).append(": ").append(grade).append(" | ");
            }

            studentTableModel.addRow(new Object[]{
                s.id, s.name, coursesStr.toString(), gradesStr.toString()
            });
        }

        // Refresh ComboBoxes without triggering concurrent modification
        Student currentEnrollSelection = (Student) enrollStudentCombo.getSelectedItem();
        Student currentGradeSelection = (Student) gradeStudentCombo.getSelectedItem();

        enrollStudentCombo.removeAllItems();
        gradeStudentCombo.removeAllItems();

        for (Student s : students) {
            enrollStudentCombo.addItem(s);
            gradeStudentCombo.addItem(s);
        }

        // Restore selections if possible
        if (currentEnrollSelection != null) enrollStudentCombo.setSelectedItem(currentEnrollSelection);
        if (currentGradeSelection != null) gradeStudentCombo.setSelectedItem(currentGradeSelection);
    }

    // --- Main Method ---
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new StudentManagerApp().setVisible(true);
        });
    }
}

