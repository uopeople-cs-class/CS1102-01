/*
    This file runs a program which is a minimal library system where users can get, add, borrow, and return books
*/

// importing the necessary libs
import java.util.ArrayList;
import java.util.Scanner;

// creating a book class object
class Book {
    private String title;
    private String author;
    public int quantity;
    public int borrowedCount;
    public Boolean isAvailable;

    // Constructor
    public Book(String title, String author, int quantity, int count){
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.isAvailable = true;
        this.borrowedCount = count;
    }

    // get title
    public String getTitle(){
        return title;
    }
    // get author
    public String getAuthor(){
        return author;
    }
    // get quantity
    public int getQuantity(){
        return quantity;
    }
    // get borrowed count
    public int getBorrowedCount(){
        return borrowedCount;
    }
    // get Availability
    public Boolean getAvailability(){
        return isAvailable;
    }
}

// Creating the file class
public class paTwo {

    // Main method serving as the enry point of the program
    public static void main(String[] args) {
        // Create the shelf here
        ArrayList<Book> shelf = new ArrayList<>();
        // System Instructions
        System.out.println("==========================================================");
        System.out.println("======== Welcome to Davisone's Community Library =========");
        System.out.println("==========================================================");
        System.out.println("Use the following options to navigate through the library:"); 
        // get user input:
        Scanner userInput = new Scanner(System.in);
        // Entering into a loop to run the program forever
        while (true) {
            try {
                // Options
                System.out.println("Main menu\n Options \n1. get books \n2. donate or add books \n3. borrow books \n4. return books \n5. exit");
                String option = userInput.nextLine();
                int options = Integer.parseInt(option);
                // controllers
                switch (options) {
                    case 1:
                        if (shelf.size() < 1){
                            System.out.println("Sorry no books available, please contact the liberian to add books");
                            break;
                        }
                        System.out.println();
                        System.out.println("=================================================================");
                        System.out.println("========================= Available Books =======================");
                        System.out.println("=================================================================");
                        getBooks(shelf);
                        System.out.println("Use the following options to add or borrow a book. Or go back to main menu");
                        System.out.println("\n1. add books \n2. borrow books \n3. back \n4. exit");
                        String getBookOptions = userInput.nextLine();
                        int getBookOption = Integer.parseInt(getBookOptions);
                        switch (getBookOption) {
                            case 1:
                                shelf = addBooks(shelf);
                                System.out.println();
                                getBooks(shelf);
                                // go back
                                break;
                            case 2:
                                shelf = borrowBooks(shelf);
                                System.out.println();
                                getBooks(shelf);
                                // go back
                                break;
                            case 3:
                                // go back to main menu
                                break;
                            case 4:
                                // exit the program
                                System.exit(200);
                            default:
                                userInput.close();
                                throw new Exception();
                        }
                        break;
                    case 2:
                        System.out.println("=================================================================");
                        System.out.println("===== Thank you for adding or donating book(s) to the shelf =====");
                        System.out.println("=================================================================");
                        shelf = addBooks(shelf);
                        System.out.println();
                        getBooks(shelf);
                        // back
                        break;
                    case 3:
                        if (shelf.size() < 1){
                            System.out.println("Sorry no books available, please contact the liberian to add books");
                            break;
                        }
                        System.out.println("=================================================================");
                        System.out.println("============ These are the books available to borrow ============");
                        System.out.println("=================================================================");
                        getBooks(shelf);
                        // now borrow the books
                        shelf = borrowBooks(shelf);
                        System.out.println();
                        getBooks(shelf);
                        break;
                    case 4:
                        System.out.println("=================================================================");
                        System.out.println("===== Thank you for returning our book(s) back to the shelf =====");
                        System.out.println("=================================================================");
                        shelf = returnBook(shelf);
                        System.out.println();
                        getBooks(shelf);
                        break;
                    case 5:
                        System.exit(200);
                    default:
                        userInput.close();
                        throw new Exception();
                }
            }
            catch (Exception e) {
                System.out.println("Please enter a valid option\n");
            }
        }
    }


    // creating the get book functions
    static void getBooks(ArrayList<Book> shelfs){
        // using the for loop to iterate through the Array
        int countLimit = shelfs.size();
        int count = 1;
        for(Book book : shelfs) {
            if (count <= countLimit && book.isAvailable == true){
                System.out.print(count + " Title: " + book.getTitle() +", ");
                System.out.print("Author: " + book.getAuthor() + ", ");
                System.out.print("Quantity: " + book.getQuantity() + ", ");
                System.out.print("Available: " + book.getAvailability() + "\n");
            }
            count++;
        }
        System.out.println();
    }

    // creating the add book functions
    static ArrayList<Book> addBooks(ArrayList<Book> shelfs){
        
        // get the book details, Note that book can only be added one after the other
        while (true) { 
            Scanner getDetails = new Scanner(System.in);
            try {
                System.out.println("Title: ");
                String title = getDetails.nextLine();
                System.out.println("Author: ");
                String author = getDetails.nextLine();
                System.out.println("Quantity: ");
                String quantity = getDetails.nextLine();
                int bq = Integer.parseInt(quantity);

                // control statements
                if (title.length() < 1){
                    throw new Exception();
                }
                if (author.length() < 1){
                    throw new Exception();
                }
                if (!shelfs.isEmpty()){
                    for(Book book: shelfs){
                        if(book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)){
                            book.quantity += bq;
                            System.out.print("\nBook added successfully; " + shelfs.getLast().getTitle() + "\n");
                            System.out.println("Use 1 or 2 to select the options\nDo you want to add another book?: \n1. Yes\n2. No");
                            String response = getDetails.nextLine();
                            int option = Integer.parseInt(response);
                            if (option == 1){
                                addBooks(shelfs);
                            }
                            return shelfs;
                        }
                    }
                }
                // add book to shelf
                Book book = new Book(title, author, bq, 0); // count: 0 because none has been borrowed yet
                shelfs.add(book);
                System.out.print("\nBook added successfully; " + shelfs.getLast().getTitle() + "\n");
                System.out.println("Use 1 or 2 to select the options\nDo you want to add another book?: \n1. Yes\n2. No");
                String response = getDetails.nextLine();
                int option = Integer.parseInt(response);
                if (option == 1){
                    addBooks(shelfs);
                }
                return shelfs;

            } catch (Exception e) {
                System.out.print("Sorry, It seems you entered an invalid detail, please try again!\n");
            }
        }
    }

    // Implementing the borrow book function
    static ArrayList<Book> borrowBooks(ArrayList<Book> shelfs){
        // now get the book the user wants to borrow, 
        System.out.println();
        System.out.print("Please enter the title of the book you want to borrow\nignore case sensitivity!\n");
        Scanner getInfo = new Scanner(System.in);
        while (true) { 
            try {
                System.out.print("Title: ");
                String borrowTitle = getInfo.nextLine();
                System.out.print("Author: ");
                String author = getInfo.nextLine();
                boolean found = false;
                for(Book book : shelfs){
                    if(book.getTitle().equalsIgnoreCase(borrowTitle) && book.getAuthor().equalsIgnoreCase(author)){
                        found = true;
                        if(book.getQuantity() >= 1){
                            book.borrowedCount++;
                            book.quantity--;
                            if(book.quantity == 0){
                                book.isAvailable = false;
                            }
                            System.out.print("Book checked out successfully! Please do well to return it on time.\n");
                            System.out.println("Use 1 or 2 to select the options\nDo you want to borrow another book?: \n1. Yes\n2. No");
                            String response = getInfo.nextLine();
                            int option = Integer.parseInt(response);
                            if (option == 1){
                                System.err.println();
                                getBooks(shelfs);
                                System.err.println();
                                borrowBooks(shelfs);
                            }
                            return shelfs;
                        }
                        System.out.print("Sorry, this book is currently unavailable.\n");
                    }
                }
                if(!found){
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.print("Sorry, Book not Found, please try again!\n");
            }
        }
    }

    // implementing the return book method
    static ArrayList<Book> returnBook(ArrayList<Book> shelfs){
        // Get the details of the book returned
        Scanner getInfo = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Title: ");
                String title = getInfo.nextLine();
                System.out.println("Author: ");
                String author = getInfo.nextLine();
                for(Book book : shelfs){
                    if(book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)){
                        book.quantity++;
                        book.borrowedCount--;
                        if(book.quantity > 0 && book.isAvailable == false){
                            book.isAvailable = true;
                        }
                        System.out.println("Book returned successfully!");
                        System.out.println("Use 1 or 2 to select the options\nDo you want to return another book?: \n1. Yes\n2. No");
                        String response = getInfo.nextLine();
                        int option = Integer.parseInt(response);
                        if (option == 1){
                            returnBook(shelfs);
                        }
                        return shelfs;
                    }
                }
                throw new Exception();
            } catch (Exception e) {
                System.err.println("Sorry, book not registered in our system");
            }
        }
    }
}