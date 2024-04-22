package virtualpet;
import java.util.Scanner;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.*;

public class VirtualPet {
        //Declare global variables for pet stats
        static int maxHealth = 0;
        static int currentHealth = 0;
        static int maxFood = 0;
        static int currentFood = 0;
        static int maxEnergy = 0;
        static int currentEnergy = 0;
        static int coins = 0;
        static boolean mainMenu = false;
        static String username = "";
        static String password = "";
        
    public static void main(String[] args) {
        //Declare variables
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        int menuOption = 0;
        int tempCoins = 0;
        
        //Menu Screen
        System.out.println("    _____        ^----^ \n / /^ . ^\\ \\    |^ Y ^| \n  / (_U_) \\    //      \\\\  \n /	   \\   (,,) (,,)");
        System.out.println("      Pet Adventures!");
        
        
        //First main menu
        //If username and password is correct, go to menu
        if(login()){
            do{
                System.out.print("Welcome, " +  username + "!\n1.Start Game   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");
                menuOption = keyboard.nextInt();

                //Determine where the user wants to go
                switch(menuOption){
                    //If user chooses to start the game, they are given choices of their starting pet
                    case 1: 
                        petSelector();
                        statPointsAssign(nameSelector());
                        mainMenu = true;
                        break;
                    //If user chooses to read instructions, they will be brought to the instructions
                    case 2: 
                        System.out.println("Here are the instructions!"); 
                        break;
                    //If user chooses to leave the game, the program will stop
                    case 3: System.out.println("Exiting..."); System.exit(0); break;
                    default: System.out.println("Invalid input");
                }
            } while (menuOption != 3 && mainMenu == false);
        }
        
        //Second main menu
        //Looping the main menu after a user has chosen their pet while they have not chosen to exit the program
        do{
            //Ask user for input
            System.out.print("\nWelcome, " + username + "!\nYou currently have " + coins + " coins\n1.Play/Interact   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");
            menuOption = keyboard.nextInt();

            //Determine what to do depending on user input
            switch(menuOption){
                //If user chooses to start playing
                case 1: 
                    mainMenu = false;
                    System.out.println("\nMinigames:\n1.Number Guessing Game\n2.Matching Game\nInteraction:\n3.Play with your pet\n4.Feed your pet\n5.Groom your pet");
                    System.out.print("Where would you like to go: ");
                    int gameChoice = keyboard.nextInt();
                    switch(gameChoice){
                        //User chooses to play number guessing minigame
                        case 1:
                            coins += numberGuessingGame();
                            mainMenu = true;
                            break;
                        //User chooses to play matching minigame
                        case 2:
                            coins += matchingGame();
                            mainMenu = true;
                            break;
                        case 3:
                            playPet();
                            mainMenu = true;
                            break;
                        case 4:
                            feedPet();
                            mainMenu = true;
                            break;
                        case 5:
                            groomPet();
                            mainMenu = true;
                            break;
                        default: System.out.print("Invalid Input");
                    }
                    break;
                //If user wants to see the instructions
                case 2: 
                    mainMenu = false;
                    System.out.println("Here are the instructions!"); 
                    mainMenu = true;
                    break;
                //If user wants to exit the game
                case 3: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid input");
            }
        } while (menuOption != 3 && mainMenu == true);
        System.exit(0);
    }
    
    //Login system    
    public static boolean login(){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Are you a new user (Y/N)? ");
        String newUser = (keyboard.next()).toLowerCase();
        boolean goToFirstMenu = false;
        username = "";
        password = "";
        
        if(newUser.equals("y")){
            for (int i = 1; i > 0; i++){
                try{
                    keyboard.nextLine();
                    System.out.print("Enter a new username: ");
                    username = keyboard.nextLine();
                    System.out.print("Enter a new password: ");
                    password = keyboard.nextLine();
                    
                    File newUserInfo = new File(username + ".txt");
                    PrintWriter output = new PrintWriter(newUserInfo);
                    output.print(username + "\n" + password);
                    output.close();
                    
                    mainMenu = false;
                    goToFirstMenu = true;   
                    break;
                } catch (FileNotFoundException e){
                    System.out.println("Error! Can't Open!");
                }
            }            
        }
        else if(newUser.equals("n")){
            //Gives user 3 chances to enter correct username and password
            try{
                for (int i = 0; i < 3; i++){
                    username = JOptionPane.showInputDialog("Enter your username");
                    password = JOptionPane.showInputDialog("Enter your password");
                    File userInfo = new File(username + ".txt");
                    if(userInfo.exists()){
                        Scanner input = new Scanner(userInfo);
                        String correctUsername = input.nextLine();
                        String correctPassword = input.nextLine();
                        input.close();

                        //If username and password is correct, go to return
                        if ((username.equals(correctUsername)) && (password.equals(correctPassword))){
                            break;
                        }
                        //If guesses exceed 3, exit program
                        else if(i == 2){
                            //System.out.println("You have guessed too many times. Login Failed.");
                            JOptionPane.showMessageDialog(null, "You have guessed too many times. Login Failed.");
                            System.exit(0);
                        }
                        //If user guesses wrong, output and start next loop/guess
                        else{
                            //System.out.println("Incorrect username or password. Try again.");
                            JOptionPane.showMessageDialog(null, "Incorrect password. Try again.");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect username. Try again.");
                    }
                }
                //If username and password are correct, tell program to go to second main menu 
                mainMenu = true;
                goToFirstMenu = false;
            } catch (FileNotFoundException e){
                System.out.println("Error! Can't Open!");
            }
        }
        return goToFirstMenu;
    }   
    
    //Pet Selector
    public static String petSelector(){
        Scanner keyboard = new Scanner(System.in);
        String userPet = "";
        
        //Keep looping until user gives valid response
        for (int i = 1; i > 0; i++){
            System.out.print("\nOption 1: Dog   Option 2: Cat \nChoose a pet: ");
            int petChoice = keyboard.nextInt();
            
            //Depending on user input, determine pet choice
            if(petChoice == 1){
                System.out.println("You have chosen the Dog!");
                userPet = "dog";
                break;
            }
            else if(petChoice == 2){
                System.out.println("You have chosen the Cat!");
                userPet = "cat";
                break;
            }
            else{
                System.out.println("Invalid input");
            }
        }
        //Return the pet chosen
        return userPet;
    }
    
    //Name Selector
    public static String nameSelector(){
        Random rand = new Random();
        String petName = "";
        
        for(int i = 1; i > 0; i++){
            //System.out.print("\nChoose a name for your pet! Would you like to...\n1.Type in a name\n2.Randomly generate a name\nChoose: ");
            //int nameChoice = keyboard.nextInt();
            //keyboard.nextLine();
            String choice = JOptionPane.showInputDialog("Choose a name for your pet! Would you like to...\n1.Type in a name\n2.Randomly generate a name");
            int nameChoice = Integer.parseInt(choice);
            
            //If user chooses to pick their own name
            if(nameChoice == 1){
                petName = JOptionPane.showInputDialog("Enter a name for your pet");
                //System.out.print("Enter a name: ");
                //petName = keyboard.nextLine();
                break;
            } 
            //Otherwise if the user chooses a randomly generated name, use random name generator
            else if (nameChoice == 2){
                int nameLength = rand.nextInt(5)+4;
                String consonants = "bcdfghjklmnpqrstvwxyz";
                String cConsonants = "BCDFGHJKLMNPQRSTVWXYZ";
                String vowels = "aeiou";

                //Used to alternate between consonants and vowels
                for(int j = 0; j < nameLength; j++){
                    if (j%2 == 0){
                        //For the first loop/letter in the name, choose from a list of capital letter cosntants
                        if(j==0){
                            char cConsonant = (char)cConsonants.charAt(rand.nextInt(20));
                            petName += cConsonant;
                            continue;
                        }
                        //Otherwise choose a lowercase constant
                        char consonant = (char)consonants.charAt(rand.nextInt(20));
                        petName += consonant;
                    } 
                    //For every even letter in the name, choose a vowel
                    else if (j%2 == 1){
                        char vowel = (char)vowels.charAt(rand.nextInt(5));
                        char tempVowel = vowel;
                        petName += tempVowel;

                        //There is a 1 out of 4 chance to have a double letter appear
                        int doubleLetter = rand.nextInt(4);
                        if(doubleLetter == 0){
                            petName += tempVowel;
                            j++;
                        }
                    }
                }
                break;
            } else{
                JOptionPane.showMessageDialog(null, "Invalid Input.");
            }
        }
        //State the name of the pet and return it to be used in the stat points assignment UI
        JOptionPane.showMessageDialog(null, "Your pet, named " + petName + ", has been born!");
        //System.out.println("Your pet, named " + petName + ", has been born!\n");
        return petName;
    }

    //Pet Stat Points
    public static void statPointsAssign(String petName){
        //State variables
        Random rand = new Random();
        int totalStatPoints = 50;
        
        //Randomly assign stat points to the pet after a name is chosen
        maxHealth = rand.nextInt(20)+1;
        currentHealth = maxHealth;
        totalStatPoints -= maxHealth;
        maxFood = rand.nextInt(20)+1;
        currentFood = maxFood;
        totalStatPoints -= maxFood;
        maxEnergy = totalStatPoints;
        currentEnergy = maxEnergy;

        //Output the stats for the pet
        System.out.println(petName + " Stats:");
        System.out.println("Health: " + maxHealth + "\nFood: " + maxFood + "\nEnergy: " + maxEnergy);
    }
    
    //Number Guessing Minigame
    public static int numberGuessingGame(){
        //Generate a random number
        System.out.println("\nNumber Guessing Game...");
        Random rand = new Random();
        Scanner keyboard = new Scanner(System.in);
        int randNum = rand.nextInt(98) + 2;
        int tempCoins = 0;

        //Ask user to guess, and they have 10 tries
        System.out.print("Guess a number between 1-100: ");
        int guess = keyboard.nextInt();
        for (int i = 0; i < 9; i++){
            //Tell the user if their guess it too high or low
            if (guess > randNum){
                System.out.println("Too high! Try again!");
            }
            else if (guess < randNum){
                System.out.println("Too low! Try again!");
            }
            //If guessed correctly, the user will get some coins based on number of guesses used
            else if (guess == randNum){
                tempCoins += 100 * (9 - i);
                System.out.println("You got it correct! You guessed " + (i + 1) + " times. You earned " + tempCoins + " coins!");
                break;
            }
            //Otherwise continue asking user to guess
            System.out.print("Guess a number between 1-100: ");
            guess = keyboard.nextInt();
            //If user doesn't get the number within 10 tries, they get nothing
            if(guess != randNum && i == 9){
                System.out.println("You ran out of guesses. The number was " + randNum + ". Better luck next time!");
                tempCoins = 0;
            }
        }
        //Return number of coins earned in this minigame to main method to store total coins for the user
        return tempCoins;
    }
    
    //Matching Minigame
    public static int matchingGame(){
        //Declare variables
        System.out.println("\nMatching Game...");
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        
        String startingCards = "aabbccddeeff";
        String shuffle = "";
        String correctGuess = "";
        String noReveal = "";
        
        int shuffleLength = startingCards.length();
        int tempLetter = 0;
        int correctPairs = 0;
        int tempCoins = 0;

        //Used to shuffle the 'deck'
        for (int i = 0; i < shuffleLength; i++){
            tempLetter = rand.nextInt(startingCards.length());
            shuffle += startingCards.charAt(tempLetter);
            startingCards = (startingCards.substring(0,tempLetter)) + (startingCards.substring(tempLetter + 1,startingCards.length()));
            noReveal += "X";
        }

        //Asks user to guess the locations of matching letters based on indicies
        System.out.println("Select indicies to match the letters...\n" + noReveal + "\n0 1 2 3 4 5 6 7 8 9 10 11");
        //Users are given 30 tries
        for (int i = 0; i < 30; i++){
            System.out.print("\nIndex 1: ");
            int index1 = keyboard.nextInt();
            System.out.print("Index 2: ");
            int index2 = keyboard.nextInt();

            //If the letters at chosen indicies are equal, then reveal them
            if(shuffle.charAt(index1) == shuffle.charAt(index2)){
                System.out.println("That is correct!");
                correctPairs += 1;
                
                //Used to display the string based on every found pair
                if(correctPairs == 1){
                    correctGuess = noReveal.substring(0,index1) + shuffle.charAt(index1) + noReveal.substring((index1 + 1),index2) + shuffle.charAt(index2) + noReveal.substring((index2 + 1),noReveal.length());
                    System.out.print(correctGuess);
                } else {
                    correctGuess = correctGuess.substring(0,index1) + shuffle.charAt(index1) + correctGuess.substring((index1 + 1),index2) + shuffle.charAt(index2) + correctGuess.substring((index2 + 1),correctGuess.length());
                    System.out.print(correctGuess);
                }
            } else {
                System.out.print("They did not match. Try again!");
            }

            //If the user finds all pairs, then they are given coins based on number of guesses
            if(correctPairs == (shuffleLength/2)){
                tempCoins += 25 * (30 - i) * correctPairs;
                System.out.println("\nYou found all the pairs! You earned " + tempCoins + " coins!");
                break;
            } 
            //Otherwise if user doesn't get all pairs within the limited number of tries, they get coins based on number of found pairs
            else if((correctPairs != (shuffleLength/2)) && (i == 19)){
                tempCoins += 25 * correctPairs;
                System.out.println("\nYou only found " + correctPairs + " pairs. Try again next time! You earned " + tempCoins + " coins!");
                break;
            }
        }
        //Return the coins earned in this minigame to the main method to store a total number of coins
        return tempCoins;
    }
    
    //Playing with your pet
    public static void playPet(){
        Scanner keyboard = new Scanner(System.in);
        //Keep asking the question until user responds
        for(int i = 1; i > 0; i++){
            System.out.print("Would you like to give your pet a toy?(Y/N) ");
            String giveToy = (keyboard.nextLine()).toLowerCase();
            int toyPrice = 45;
            
            //Ensures that user has enough amount of coins to pay for a toy
            if(coins >= toyPrice){
                //If user chooses to give pet a toy
                if(giveToy.equals("y")){
                    System.out.println("You gave your pet a toy!");
                    coins -= toyPrice;
                    currentEnergy += 5;
                    //Ensures that current energy doesn't exceed the limit
                    if(currentEnergy > maxEnergy){
                        currentEnergy = maxEnergy;
                    }
                    System.out.println("Your pet is feeling more energetic! Your pet has " + currentEnergy + "/" + maxEnergy + " energy!\nCoins -" + toyPrice);
                    break;
                }
                //If user doesn't choose to give their pet a toy
                else if(giveToy.equals("n")){
                    System.out.println("You didn't give your pet a toy...");
                    break;
                } else {
                    System.out.println("Invalid input");
                } 
            }
            //If user doesn't have enough coins
            else {
                System.out.println("You don't have enough coins! Play a minigame to earn some!");
                break;
            }
        }
    }
    
    //Feeding your pet
    public static void feedPet(){
        Scanner keyboard = new Scanner(System.in);
        //Keep asking the question until user responds
        for(int i = 1; i > 0; i++){
            System.out.print("Would you like to feed your pet?(Y/N) ");
            String giveFood = (keyboard.nextLine()).toLowerCase();
            int foodPrice = 35;
            
            //Ensures that user has enough amount of coins to pay for food
            if(coins >= foodPrice){
                //If user chooses to feed pet
                if(giveFood.equals("y")){
                    System.out.println("You fed your pet!");
                    coins -= foodPrice;
                    currentFood += 5;
                    //Ensures that current food doesn't exceed the limit
                    if(currentFood > maxFood){
                        currentFood = maxFood;
                    }
                    System.out.println("You pet is less hungry! Your pet has " + currentFood + "/" + maxFood + " food!\nCoins -" + foodPrice);
                    break;
                }
                //If user doesn't choose to feed pet
                else if(giveFood.equals("n")){
                    System.out.println("You didn't feed your pet...");
                    break;
                } else {
                    System.out.println("Invalid input");
                } 
            }
            //If user doesn't have enough coins
            else {
                System.out.println("You don't have enough coins! Play a minigame to earn some!");
                break;
            }
        }
    }
    
    //Grooming your pet
    public static void groomPet(){
        Scanner keyboard = new Scanner(System.in);
        //Keep asking the question until user responds
        for(int i = 1; i > 0; i++){
            System.out.print("Would you like to groom your pet?(Y/N) ");
            String giveGroom = (keyboard.nextLine()).toLowerCase();
            int groomPrice = 50;
            
            //Ensures that user has enough amount of coins to groom
            if(coins >= groomPrice){
                //If user chooses to groom their pet
                if(giveGroom.equals("y")){
                    System.out.println("You groomed your pet!");
                    coins -= groomPrice;
                    currentHealth += 5;
                    //Ensures that current energy doesn't exceed the limit
                    if(currentHealth > maxHealth){
                        currentHealth = maxHealth;
                    }
                    System.out.println("Your pet is feeling healthier! Your pet has " + currentHealth + "/" + maxHealth + " health!\nCoins -" + groomPrice);
                    break;
                }
                //If user doesn't choose to groom their pet
                else if(giveGroom.equals("n")){
                    System.out.println("You didn't groom your pet...");
                    break;
                } else {
                    System.out.println("Invalid input");
                } 
            }
            //If user doesn't have enough coins
            else {
                System.out.println("You don't have enough coins! Play a minigame to earn some!");
                break;
            }
        }
    }
}