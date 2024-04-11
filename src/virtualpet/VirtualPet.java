package virtualpet;
import java.util.Scanner;
import java.util.Random;

public class VirtualPet {
    public static void main(String[] args) {
        //Declare variables
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        boolean mainMenu = false;
        int menuOption = 0;
        
        int coins = 0;
        int tempCoins = 0;
        
        //Menu Screen
        System.out.println("    _____        ^----^ \n / /^ . ^\\ \\    |^ Y ^| \n  / (_U_) \\    //      \\\\  \n /	   \\   (,,) (,,)");
        System.out.print("      Pet Adventures!");
        
        
        //If username and password is correct, go to menu
        if(login()){
            do{
                System.out.print("\nWelcome, Player!\n1.Start Game   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");
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
        
        //Looping the main menu after a user has chosen their pet while they have not chosen to exit the program
        do{
            //Ask user for input
            System.out.print("\nWelcome, Player!\nYou currently have " + coins + " coins\n1.Play/Interact   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");
            menuOption = keyboard.nextInt();

            //Determine what to do depending on user input
            switch(menuOption){
                //If user chooses to start playing
                case 1: 
                    mainMenu = false;
                    System.out.print("1.Number Guessing Game\n2.Matching Game\nSelect a minigame: ");
                    int gameChoice = keyboard.nextInt();
                    switch(gameChoice){
                        //User chooses to play number guessing minigame
                        case 1:
                            coins += numberGuessingGame();
                            break;
                        //User chooses to play matching minigame
                        case 2:
                            coins += matchingGame();
                            break;
                        default: System.out.print("Invalid Input");
                    }
                case 2: 
                    mainMenu = false;
                    System.out.println("Here are the instructions!"); 
                    mainMenu = true;
                    break;
                case 3: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid input");
            }
        } while (menuOption != 3 && mainMenu == true);
        System.exit(0);
    }
    
    //Login system    
    public static boolean login(){
        Scanner keyboard = new Scanner(System.in);
        
        for (int i = 0; i < 3; i++){
            System.out.print("\nEnter username: ");
            String username = keyboard.nextLine();
            System.out.print("Enter password: ");
            String password = keyboard.nextLine();
            if ((username.equals("snoopy")) && (password.equals("toto"))){
                break;
            }
            else if(i == 2){
                System.out.println("You have guessed too many times. Login Failed.");
                System.exit(0);
            }
            else{
                System.out.println("Incorrect username or password. Try again.");
            }
        }
        return true;
    }   
    
    //Pet Selector
    public static String petSelector(){
        Scanner keyboard = new Scanner(System.in);
        String userPet = "";
        
        for (int i = 1; i > 0; i++){
            System.out.print("\nOption 1: Dog   Option 2: Cat \nChoose a pet: ");
            int petChoice = keyboard.nextInt();
            
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
        return userPet;
    }
    
    //Name Selector
    public static String nameSelector(){
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        String petName = "";
        
        for(int i = 1; i > 0; i++){
            System.out.print("\nChoose a name for your pet! Would you like to...\n1.Type in a name\n2.Randomly generate a name\nChoose: ");
            int nameChoice = keyboard.nextInt();
            keyboard.nextLine();

            //If user chooses to pick their own name
            if(nameChoice == 1){
                System.out.print("Enter a name: ");
                petName = keyboard.nextLine();
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
                        //For the first loop/letter in the name, choose from a list of capital letters
                        if(j==0){
                            char cConsonant = (char)cConsonants.charAt(rand.nextInt(20));
                            petName += cConsonant;
                            continue;
                        }
                        char consonant = (char)consonants.charAt(rand.nextInt(20));
                        petName += consonant;
                    } else if (j%2 == 1){
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
                System.out.println("Invalid input");
            }
        }
        System.out.println("Your pet, named " + petName + ", has been born!\n");
        return petName;
    }

    //Pet Stat Points
    public static boolean statPointsAssign(String petName){
        Random rand = new Random();
        Scanner keyboard = new Scanner(System.in);
        int maxHealth = 0;
        int maxFood = 0;
        int maxEnergy = 0;
        int totalStatPoints = 20;
        
        //Randomly assign stat points to the pet after a name is chosen
        maxHealth = rand.nextInt(10)+1;
        totalStatPoints -= maxHealth;
        maxFood = rand.nextInt(10)+1;
        totalStatPoints -= maxFood;
        maxEnergy = totalStatPoints;

        //Output the stats for the pet
        System.out.println(petName + " Stats:");
        System.out.println("Health: " + maxHealth + "\nFood: " + maxFood + "\nEnergy: " + maxEnergy);
        return true;
    }
    
    //Number Guessing Minigame
    public static int numberGuessingGame(){
        //Generate a random number
        System.out.println("\nNumber Guessing Game...");
        Random rand = new Random();
        Scanner keyboard = new Scanner(System.in);
        int randNum = rand.nextInt(98) + 2;
        int tempCoins = 0;

        System.out.print("Guess a number between 1-100: ");
        int guess = keyboard.nextInt();
        for (int i = 0; i < 9; i++){
            if (guess > randNum){
                System.out.println("Too high! Try again!");
            }
            else if (guess < randNum){
                System.out.println("Too low! Try again!");
            }
            else if (guess == randNum){
                tempCoins += 100 * (9 - i);
                System.out.println("You got it correct! You guessed " + (i + 1) + " times. You earned " + tempCoins + " coins!");
                break;
            }
            System.out.print("Guess a number between 1-100: ");
            guess = keyboard.nextInt();
            if(guess != randNum && i == 9){
                System.out.println("You ran out of guesses. The number was " + randNum + ". Better luck next time!");
                tempCoins = 0;
            }
        }
        return tempCoins;
    }
    
    //Matching Minigame
    public static int matchingGame(){
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

        for (int i = 0; i < shuffleLength; i++){
            tempLetter = rand.nextInt(startingCards.length());
            shuffle += startingCards.charAt(tempLetter);
            startingCards = (startingCards.substring(0,tempLetter)) + (startingCards.substring(tempLetter + 1,startingCards.length()));
            noReveal += "X";
        }

        System.out.println("Select indicies to match the letters...\n" + noReveal);
        for (int i = 0; i < 20; i++){
            System.out.print("\nIndex 1: ");
            int index1 = keyboard.nextInt();
            System.out.print("Index 2: ");
            int index2 = keyboard.nextInt();

            if(shuffle.charAt(index1) == shuffle.charAt(index2)){
                System.out.println("That is correct!");
                correctPairs += 1;
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

            if(correctPairs == (shuffleLength/2)){
                tempCoins += 25 * (20 - i) * correctPairs;
                System.out.println("\nYou found all the pairs! You earned " + tempCoins + " coins!");
                break;
            } else if((correctPairs != (shuffleLength/2)) && (i == 19)){
                tempCoins += 25 * correctPairs;
                System.out.println("\nYou didn't final all pairs. Try again next time! You earned " + tempCoins + " coins!");
                break;
            }
        }
        return tempCoins;
    }
}