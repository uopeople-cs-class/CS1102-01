/*
    This file contains a stock price list. Using an array to return the maximum price, ...
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class paFour {
    // in here just do the list and methods
    public static void main(String[] args) {
        int[] dailyPrices = {230, 200, 900, 342, 700, 433, 500, 900, 900, 900};
        // before you can print all use a loop
        float mean = calculateAveragePrice(dailyPrices);
        System.out.println(mean);
        double max = findMaximumPrice(dailyPrices);
        System.out.println(max);
        int occurance = countOccurance(dailyPrices);
        System.out.println(occurance);
        ArrayList<Integer> dailyPriceList =
            new ArrayList<>(Arrays.asList(230, 200, 900, 342, 700, 433, 500, 900, 900, 900));
        ArrayList<Integer> cumulativePriceList = computeCumulativeSum(dailyPriceList);
        System.out.println(cumulativePriceList);
    }


    // calculate average price
    static float  calculateAveragePrice(int[] dps){
        // what this function does is that, it takes the list and calculate avg
        int counter = 0;
        int total = 0;
        for (int i : dps) {
            total += i;
            counter++;
        }
        float  avg = total/counter;
        return avg;
    }

    // find the maximum number
    static double findMaximumPrice(int[] dps){
        int maxNumber = 0;
        for (int x : dps) {
            if(x>maxNumber){
                maxNumber = x;
            }
        }
        return maxNumber;
    }

    // this method gets an input value from the user and count the number of time the values is in the list
    static int countOccurance(int[] dps) {
        // set the counted to zero
        int count = 0;
        // takes the input from the user
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        while (true) { 
            try {
                // get the input
                System.out.println("Enter the price you want to count it occurance:");
                int value = Integer.parseInt(input.nextLine());
                for (int i : dps) {
                    if (i == value){
                        // update count
                        count++;
                    }
                }
                return count;
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid input, please enter a valid integer.");
            }
        }
    }

    // cumulative sum
    static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> dpsl){
        ArrayList<Integer> cumList = new ArrayList<>();
        int cum = 0;
        for (int i = 0; i < dpsl.size(); i++) {
            if(i > 0){
                cum = cum + dpsl.get(i);
                cumList.add(cum);
            }
            if(i == 0){
                cum = dpsl.get(i);
                cumList.add(cum);
            }
        }
        return cumList;
    }

}