// Import the necessary lib for getting user input
import java.util.Scanner;

public class exerciseThree {
    public static void main(String[] args) {
        // creat the scanner object
        Scanner scanner = new Scanner(System.in);

        // get the users input and save it to name
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        // Display the greetings to the user
        System.out.print("Hello, " + name.toUpperCase());

        // now close the scanner
        scanner.close();
    }
}
