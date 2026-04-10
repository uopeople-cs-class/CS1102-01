public class practice {
    public static void main(String[] args) throws Exception {
        // initialize the variables
        int firstDice;
        int secondDice;
        int totalRoll;

        // get random values and assign it to firstDice and secondDice
        firstDice = (int)(Math.random()*6)+1;
        secondDice =(int)(Math.random()*6)+1;

        // compute the total
        totalRoll = firstDice + secondDice;

        // display output
        System.out.println("Your first Dice came up as:");
        System.out.println(firstDice);
        System.out.println("Your second Dice came up as:");
        System.out.println(secondDice);
        System.out.println("Your total Dice roll is:");
        System.out.println(totalRoll);
    }
}
