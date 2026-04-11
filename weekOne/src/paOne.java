/*
    This program is a quiz program, it should ask five questions and provide four different choices
*/

// import the scanner for user inputs
import java.util.Scanner;

// class the file name
public class paOne{
    public static void main(String[] args) {
        // call the quiz function here and assign it to marks
        int marks = quiz();

        // display result
        System.out.println("\n===========================================");
        System.out.println("You answered " + marks + " correct out of 5");
    }

    public static int quiz(){
        // this function sets the question and returns the number of correct answers over the total marks
        // set the marks counter
        int correctAnswers = 0;
        // set the questions
        String questionOne = "1. Two teams are playing a footbal match!, what is the probability of team A win or draw?";
        String optionsQone = "A. 50.00% \nB. 33.33% \nC. 66.67% \nD. 25.00%\n";
        String questionTwo = "\n2. Which country has tallest tower in the world";
        String optionsQtwo = "A. UAE \nB. Ghana \nC. Turkey \nD. South Africa\n";
        String questionThree = "\n3. What is the name of the programmer of this quiz";
        String optionsQthree = "A. Davisone \nB. Chris \nC. Doris \nD. Erica\n";
        String questionFour = "\n4. If 3x + 4y = 5; what will be the answer for 27 exponent x multiplied by 81 exponent y?";
        String optionsQfour = "A. 2187 exponent xy \nB. 243 \nC. 2187 \nD. 243 exponent xy\n";
        String questionFive = "\nBonus Question\n5. Is programming in java difficult?";
        String optionsQfive = "A. No \nB. Yes \nC. Maybe \nD. Can't Tell\n";
        // use switches
        int questionNumber = 1;
        switch (questionNumber) {
            case 1:
                System.out.println(questionOne);
                System.out.print(optionsQone);
                char userAnswer1 = getOption();
                if (userAnswer1 == 'C'){
                    correctAnswers++;
                }
            case 2:
                System.out.println(questionTwo);
                System.out.print(optionsQtwo);
                char userAnswer2 = getOption();
                if (userAnswer2 == 'A'){
                    correctAnswers++;
                }
            case 3:
                System.out.println(questionThree);
                System.out.print(optionsQthree);
                char userAnswer3 = getOption();
                if (userAnswer3 == 'A'){
                    correctAnswers++;
                }
            case 4:
                System.out.println(questionFour);
                System.out.print(optionsQfour);
                char userAnswer4 = getOption();
                if (userAnswer4 == 'B'){
                    correctAnswers++;
                }
            case 5:
                System.out.println(questionFive);
                System.out.print(optionsQfive);
                char userAnswer5 = getOption();
                if (userAnswer5 == 'A'){
                    correctAnswers++;
                }
            break;
        }
        return correctAnswers;
    }

    public static char getOption(){
        // use the scanner here
        Scanner scanner = new Scanner(System.in);
        while (true) { 
            try {
                String option = scanner.nextLine();
                while (option.length() == 1){
                    switch (option.toUpperCase()) {
                        case "A":
                            return 'A';
                        case "B":
                            return 'B';
                        case "C":
                            return 'C';
                        case "D":
                            return 'D';    
                        default:
                            throw new Exception("Please select from the available options");
                    }
                }
                if (option.length() > 1){
                    throw new Exception("No words! Characaters only");
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
