/*
    This program is a quiz program, it should ask five questions and provide four different choices
*/

// import the scanner for user inputs
import java.util.Scanner;

// class the file name
public class paOne{
    public static void main(String[] args) {
        // Tell the user the exams instruction
        System.out.println("Exams about to start. \nPlease pay close attention to the following rules \n 1. Use letter A,B,C, or D to answer the question. \n 2. You can ignore any case sensitivity issues. \nAll the best.\n");

        // call the quiz function here and assign it to marks
        double marks = quiz();

        // Calculate percetage
        double score = (marks / 5) * 100;

        // display result
        System.out.println("\n====================================================");
        System.out.println("You answered " + (int)marks + " question(s) correct out of 5 question\n");
        System.out.println("Calculating percentage ...");
        // display percentage
        System.out.println("You scored: " + score + "%\n");
    }

    public static double quiz(){
        // this function sets the question and returns the number of correct answers over the total marks
        // set the marks counter
        double correctAnswers = 0;
        // set the questions
        String questionOne = "1. Two teams are playing a football match!, what is the probability of team A win or draw?\n Options:";
        String optionsQone = "A. 50.00% \nB. 33.33% \nC. 66.67% \nD. 25.00%\n";
        String questionTwo = "\n2. Which country has tallest tower in the world?\n Options:";
        String optionsQtwo = "A. UAE \nB. Ghana \nC. Turkey \nD. South Africa\n";
        String questionThree = "\n3. What is the name of the programmer of this quiz?\n Options:";
        String optionsQthree = "A. Davisone \nB. Chris \nC. Doris \nD. Erica\n";
        String questionFour = "\n4. If 3x + 4y = 5; what will be the answer for 27 exponent x multiplied by 81 exponent y?\n Options:";
        String optionsQfour = "A. 2187 exponent xy \nB. 243 \nC. 2187 \nD. 243 exponent xy\n";
        String questionFive = "\nBonus Question\n5. Is programming in java difficult?\n Options:";
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
        
        while (true) { 
            // use the scanner here
            Scanner scanner = new Scanner(System.in);
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
                if (option.length() <= 0){
                    throw new Exception("No Character detected, Please enter either A, B, C, or D to answer the question");
                }
                if (option.length() > 1){
                    throw new Exception("No words! Characaters only");
                }
                scanner.close();
            } 
            catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
