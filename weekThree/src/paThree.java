/* 
    This is a robust Student Records Management System that empower administrators with efficient tools
    for handlng student records.
*/

// Fuctions => add new student, update student info, view students - view student details embedded

// importing the necessary libs
import java.util.ArrayList;
import java.util.Scanner;

// class Object
class Student{
    // student details
    private String ID; // this is private so that it cannot be updated and it should be unique
    public String name;
    public int age;
    public String grade;

    public Student(String ID, String name, int age, String grade){
        // This is a constructor
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // now these methods get details from the student Object
    // get ID
    public String getID(){
        return ID;
    }
    
    // get name
    public String getName(){
        return name;
    }

    // get age
    public int getAge(){
        return age;
    }

    // get grade 
    public String getGrade(){
        return grade;
    }
}

// class file
public class paThree{
    // the main method
    public static void main(String[] args){
        // initialise the student folder
        ArrayList<Student> folder = new ArrayList<Student>();
        // System Instructions
        System.out.println("==========================================================");
        System.out.println("============ Student Record Management System ============");
        System.out.println("==========================================================");
        System.out.println("Use the following options to navigate through the system:"); 
        // System input
        Scanner userInput = new Scanner(System.in);
        // Infinite loop to keep program running
        while (true) {
            try{
                // Options
                System.out.println("\nMain menu\n Options \n1. add student \n2. update student \n3. veiw students \n4. exit\n");
                int options = Integer.parseInt(userInput.nextLine());
                // switches
                switch (options) {
                    case 1:
                        System.out.println("=================================================================");
                        System.out.println("================== Student Registration Portal ==================");
                        System.out.println("=================================================================\n");
                        folder = addStudent(folder);
                        break;
                    case 2:
                        System.out.println("=================================================================");
                        System.out.println("===================== Update Student Details ====================");
                        System.out.println("=================================================================\n");
                        folder = updateStudent(folder);
                        break;
                    case 3:
                        System.out.println("=================================================================");
                        System.out.println("====================== View Student Database ====================");
                        System.out.println("=================================================================\n");
                        viewStudent(folder);
                        break;
                    case 4:
                        System.exit(200);
                    default:
                        throw new Exception("Please select from available options");
                }
            }
            catch (Exception e){
                System.out.println("Failed");
            }
        }
    }

    // function 1
    // add students
    static ArrayList<Student> addStudent(ArrayList<Student> folder){
        // since the folder is initialized in the parameter
        // use the scanner here for inputs
        Scanner getDetails = new Scanner(System.in);
        while (true){
            try{
                // Get student infomation
                System.out.println("Enter Student ID (use numbers only):");
                String studentID = getDetails.nextLine();
                studentID = "STU-" + studentID;
                if (!folder.isEmpty()){
                    for(Student student : folder){
                        if (student.getID().equalsIgnoreCase(studentID)){
                            throw new Exception("Sorry, Student Id already assigned to another student\nThis ID must be unique for all students\nPlease Enter a Distinct ID\n");
                        }
                    }
                }
                System.out.println("Enter Student Name:");
                String studentName = getDetails.nextLine();
                System.out.println("Enter Student Age:");
                String studentAge = getDetails.nextLine();
                int age = Integer.parseInt(studentAge);
                System.out.println("Enter Student Grade:");
                String studentGrade = getDetails.nextLine();

                // record the details
                Student student = new Student(studentID, studentName, age, studentGrade);
                folder.add(student);

                // Display success message
                System.out.println("Student added successfully: "+ student.getName() +"\n");

                // ask if the administrator wants to add another student or return
                System.out.println("Respond to the following options\n Options \n1. Add another student.\n2.Main Menu\n");
                int option = Integer.parseInt(getDetails.nextLine());
                if (option == 1){
                    addStudent(folder);
                }
                return folder;
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // function 2
    // update student record
    static ArrayList<Student> updateStudent(ArrayList<Student> folder){
        // get the particular student by ID
        Scanner getDetails = new Scanner(System.in);
        while (true){
            try{
                // collect id from user;
                System.out.println("Enter Student ID (Example: stu-0):");
                String id = getDetails.nextLine();
                // check if id is related to a particular student.
                for (Student student: folder) {
                    if (student.getID().equalsIgnoreCase(id)){
                        // update but it should either be a specific data or all
                        System.out.println("Select option: \n1. Update student name \n2. Update student age \n3. Update student grade \n4. Update all\n");
                        int option = Integer.parseInt(getDetails.nextLine());
                        // switches
                        switch (option){
                            case 1:
                                System.out.println("Enter new name:");
                                String newName = getDetails.nextLine();
                                student.name = newName;
                                System.out.println("Name updated successfully for "+ student.getName() +"\n");
                                break;
                            case 2:
                                System.out.println("Enter new age");
                                int newAge = Integer.parseInt(getDetails.nextLine());
                                student.age = newAge;
                                System.out.println("Age updated successfully for "+ student.getName() +"\n");
                                break;
                            case 3:
                                System.out.println("Enter new grade");
                                String newGrade = getDetails.nextLine();
                                student.grade = newGrade;
                                System.out.println("Grade updated successfully for "+ student.getName() +"\n");
                                break;
                            case 4:
                                System.out.println("Enter new name:");
                                String new_name = getDetails.nextLine();
                                System.out.println("Enter new age:");
                                int new_age = Integer.parseInt(getDetails.nextLine());
                                System.out.println("Enter new grade:");
                                String new_grade = getDetails.nextLine();
                                student.name = new_name;
                                student.age = new_age;
                                student.grade = new_grade;
                                System.out.println("Records updated successfully for "+ student.getName() +"\n");
                                break;
                            default:
                                break;
                        }
                        // ask if the administrator wants to update another student or return
                        System.out.println("Respond to the following options\n Options \n1. Update another student.\n2.Main Menu\n");
                        int options = Integer.parseInt(getDetails.nextLine());
                        if (options == 1){
                            updateStudent(folder);
                        }
                        return folder;
                    }
                }
                
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    // function 3
    // viewing
    static void viewStudent(ArrayList<Student> folder){
        try {
            Scanner getOption = new Scanner(System.in);
            // iterate through the list
            if (folder.isEmpty()){
                System.out.println("Sorry, there is no student registered in the system\n Use the following options to navigate.");
                System.out.println("1. add student(s) \n2. back");
                int option = Integer.parseInt(getOption.nextLine());
                if (option == 1){
                    addStudent(folder);
                }
                throw new Exception();
            }
            int  count = 1;
            System.out.println("=====================================================");
            System.out.println("|| No. ||   ID   ||   Name   ||   Age   ||  Grade  ||");
            System.out.println("=====================================================");
            for (Student student: folder){
                System.out.println("||  " + count + "  ||  "+ student.getID() + " || " + student.getName() + " || " + student.getAge() + " || " + student.getGrade() + " ||");
                System.out.println("=====================================================");
                count++;
            }
            System.out.println("1. view a student details \n2. back");
            int option = Integer.parseInt(getOption.nextLine());
            if (option == 1){
                System.out.println("Enter student id (Ignore case sensitivity):");
                String studentID = getOption.nextLine();
                int track = 1;
                for(Student student : folder){
                    if(student.getID().equalsIgnoreCase(studentID)){
                        System.out.print("Details of student with ID:" + student.getID()+ "\n\tName: " + student.getName()+ "\n\tAge: " + student.getAge()+ "\n\tGrade: " + student.getGrade()+ "\n");
                    }
                    if (!student.getID().equalsIgnoreCase(studentID) && track >= folder.size()){
                        System.out.println("Student not found in the system");
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}

