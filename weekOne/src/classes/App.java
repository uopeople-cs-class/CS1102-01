
public class App {
    public static void main(String[] args){
        // initializing the variables
        double principal;
        double rate;
        int time;
        double interest;
        
        // assigning values to the variables
        principal = 300.0;
        rate = 17.9;
        time = 3;

        // perform computations
        interest = (principal * time * rate)/100;

        // printout results
        System.out.println("Your long awaited interest is calculated as: \n ");
        System.out.println(interest);

    }
}
