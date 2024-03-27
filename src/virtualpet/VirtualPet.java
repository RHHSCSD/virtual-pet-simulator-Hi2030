/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.Scanner;
import java.util.Random;

public class VirtualPet {
    public static void main(String[] args) {
        
        //Declare global variables
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        String userPet = "";
        boolean petChosen = false;
        boolean nameChosen = false;
        String petName = "";
        
        int maxHealth = 0;
        int maxFood = 0;
        int maxEnergy = 0;
        int totalStatPoints = 20;
        
        //Menu Screen
            System.out.println("    _____        ^----^ \n / /^ . ^\\ \\    |^ Y ^| \n  / (_U_) \\    //      \\\\  \n /	   \\   (,,) (,,)");
            System.out.print("      Pet Adventures!");
        
        //Login system
        System.out.print("\nEnter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        //If username and password is correct, go to menu
        if ((username.equals("snoopy")) && (password.equals("toto"))){
            System.out.print("\nWelcome, Player!\n1.Start   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");

            //Determine where the user wants to go
            int menuOption = input.nextInt();
            switch(menuOption){
                //If user chooses to start the game, they are given choices of their starting pet
                case 1: 
                    System.out.print("\nOption 1: Dog   Option 2: Cat \nChoose a pet: ");
                    int petChoice = input.nextInt();
                    if(petChoice == 1){
                        System.out.println("You have chosen the Dog!");
                        userPet = "dog";
                        petChosen = true;
                    }
                    else if(petChoice == 2){
                        System.out.println("You have chosen the Cat!");
                        userPet = "cat";
                        petChosen = true;
                    }
                    else{
                        System.out.println("Invalid input");
                    }
                 break;
                //If user chooses to read instructions, they will be brought to the instructions
                case 2: System.out.println("Here are the instructions!"); break;
                //If user chooses to leave the game, the program will stop
                case 3: System.out.println("Exiting..."); System.exit(0); break;
                default: System.out.println("Invalid input");
            }
        }
        //If password or username is incorrect, exit the program
        else{
            System.out.println("Incorrect username or password");
            System.exit(0);
        }
        
        //Once pet has been chosen, start name selection
        if(petChosen == true){
            System.out.print("\nChoose a name for your pet! Would you like to...\n1.Type in a name\n2.Randomly generate a name\nChoose: ");
            int nameChoice = input.nextInt();
            input.nextLine();
            //If user chooses to pick their own name
            if(nameChoice == 1){
                System.out.print("Enter a name: ");
                petName = input.nextLine();
            } 
            //Otherwise if the user chooses a randomly generated name, use random name generator
            else if (nameChoice == 2){
                int nameLength = rand.nextInt(5)+4;
                String consonants = "bcdfghjklmnpqrstvwxyz";
                String cConsonants = "BCDFGHJKLMNPQRSTVWXYZ";
                String vowels = "aeiou";
                
                //Used to alternate between consonants and vowels
                for(int i = 0; i < nameLength; i++){
                    if (i%2 == 0){
                        //For the first loop/letter in the name, choose from a list of capital letters
                        if(i==0){
                            char cConsonant = (char)cConsonants.charAt(rand.nextInt(20));
                            petName += cConsonant;
                            continue;
                        }
                        char consonant = (char)consonants.charAt(rand.nextInt(20));
                        petName += consonant;
                    } else if (i%2 == 1){
                        char vowel = (char)vowels.charAt(rand.nextInt(5));
                        char tempVowel = vowel;
                        petName += tempVowel;
                        
                        //There is a 1 out of 4 chance to have a double letter appear
                        int doubleLetter = rand.nextInt(4);
                        if(doubleLetter == 0){
                            petName += tempVowel;
                            i++;
                        }
                    }
                }
            } else{
                System.out.println("Invalid input");
                System.exit(0);
            }
            System.out.println("Your pet, named " + petName + ", has been born!\n");
            nameChosen = true;
        }
        
        //After a name is chosen, assign stat points to the pet
        if(nameChosen == true){
            //Randomly choose stat points and then assign the remainders
            maxHealth = rand.nextInt(10)+1;
            totalStatPoints -= maxHealth;
            maxFood = rand.nextInt(10)+1;
            totalStatPoints -= maxFood;
            maxEnergy = totalStatPoints;
            
            //Output the stats for the pet
            System.out.println(petName + " Stats:");
            System.out.println("Health: " + maxHealth + "\nFood: " + maxFood + "\nEnergy: " + maxEnergy);
        }
    }
}