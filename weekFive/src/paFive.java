
/*
    This file deals with all assignment for week 5
    You have been assigned to develop a Course Enrollment and Grade Management System
    in Java for a university. The system should provide functionality to enroll students
    in courses, assign grades to students, and calculate overall course grades for each 
    student. The project should demonstrate the effective utilization of static methods and
    variables to keep track of enrollment and grade-related information across multiple 
    instances of the Student and Course classes. 
    It should also showcase your ability to manipulate object state and define behavior 
    through instance methods.
*/

// necessary imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;


// student Class
class Student{
    // initialize the values
    private String id;
    private String name;
    private ArrayList<Course> enrolledCourses;
    private Map <Course, Double> grades;

    // constructor

    public Student(String id, String name, ArrayList<Course> enrolledCourse, Map<Course, Double>grade){
        this.id = id;
        this.name = name;
        this.enrolledCourses = enrolledCourse;
        this.grades = new HashMap<>();
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    public void setGrade(Course course, double grade) {
        if (enrolledCourses.contains(course)) {
            grades.put(course, grade);
        } else {
            System.out.println("Error: Student not enrolled in " + course.getCourseName());
        }
    }

    public Map<Course, Double> getGrades() {
        return grades;
    }
    
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

class Course{
    // initialization
    private String courseCode;
    private String courseName;
    private int maximumCapacity;
    private int currentEnrolled;

    // this variable keeps  track of the total students taking the course
    private int totalStudentsEnrolled;


    // the constructor
    public Course(String courseCode, String courseName, int capacity){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.currentEnrolled = 0;
        this.maximumCapacity = capacity;
    }


    // getters
    public String getCourseCode(){
        return courseCode;
    }

    public String getCourseName(){
        return courseName;
    }

    public int getMaxCapacity(){
        return maximumCapacity;
    }

    public boolean hasSpace(){
        return currentEnrolled < maximumCapacity;
    }

    public int getTotalStudentsEnrolled(){
        return totalStudentsEnrolled;
    }

    // setters
    // now we update the necessary variables here
    public void increaseEnrolls(){
        currentEnrolled++;
        totalStudentsEnrolled++;
    }
}


class CourseManagement {
    // so basically thsi class manages the course (add, get, assgin grade, calculate gpa...)
    // uses the array list
    static ArrayList<Course> courseList = new ArrayList<>();

    // add course method
    public static ArrayList<Course> addCourse(String code, String name, int capacity){
        Course course = new Course(code, name, capacity);
        courseList.add(course);
        return courseList;
    }

    // get the list of the course
    public static ArrayList<Course> getCourses(){
        return courseList;
    }

    // enrolling student to the course,
    public static void enrollStudent(Student student, Course course) {
        if (course.hasSpace()) {
            student.enrollInCourse(course);
            course.increaseEnrolls();
            System.out.println("Enrollment successful!");
        } else {
            System.out.println("Error: Course " + course.getCourseName() + " is at full capacity.");
        }
    }

    public static void assignGrade(Student student, Course course, double grade) {
        student.setGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        Map<Course, Double> grades = student.getGrades();
        if (grades.isEmpty()) return 0.0;

        double sum = 0;
        for (double g : grades.values()) {
            sum += g;
        }
        return sum / grades.size();
    }
}   

public class paFive{
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
        System.out.println("==========================================================");
        System.out.println("============ Student & Course Management System ============");
        System.out.println("==========================================================");
        System.out.println("Use the following options to navigate through the system:"); 
        // System input
            System.out.println("1. Add New Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addNewCourse(); break;
                    case 2: enrollStudent(); break;
                    case 3: assignGrade(); break;
                    case 4: calculateOverall(); break;
                    case 5: System.exit(0);
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: Please provide valid input.");
            }
        }
    }

    private static void addNewCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Max Capacity: ");
        int cap = Integer.parseInt(scanner.nextLine());
        CourseManagement.addCourse(code, name, cap);
        System.out.println("Course added.");
    }

    private static void enrollStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        Student student = findOrCreateStudent(name, id);
        displayCourses();
        System.out.print("Enter index of course: ");
        int index = Integer.parseInt(scanner.nextLine());
        
        CourseManagement.enrollStudent(student, CourseManagement.getCourses().get(index));
    }

    private static void assignGrade() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student s = findStudent(id);
        if (s == null) return;

        displayCourses();
        System.out.print("Enter index of course: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        CourseManagement.assignGrade(s, CourseManagement.getCourses().get(index), grade);
    }

    private static void calculateOverall() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student s = findStudent(id);
        if (s != null) {
            System.out.println("Overall GPA for " + s.getName() + ": " + CourseManagement.calculateOverallGrade(s));
        }
    }

    private static void displayCourses() {
        ArrayList<Course> courses = CourseManagement.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println(i + ". " + c.getCourseCode() + " - " + c.getCourseName());
        }
    }

    private static Student findStudent(String id) {
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private static Student findOrCreateStudent(String name, String id) {
        Student s = findStudent(id);
        if (s == null) {
            s = new Student(id, name, new ArrayList<>(), new HashMap<>());
            studentList.add(s);
        }
        return s;
    }
}