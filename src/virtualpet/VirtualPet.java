/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.util.Scanner;

public class VirtualPet {
    public static void main(String[] args) {
        //Declare global variables
        Scanner input = new Scanner(System.in);
        String userPet = "";
        
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
                    System.out.print("Option 1: Dog   Option 2: Cat \nChoose a pet: ");
                    int petChoice = input.nextInt();
                    if(petChoice == 1){
                        System.out.println("You have chosen the Dog!");
                        userPet = "dog";
                    }
                    else if(petChoice == 2){
                        System.out.println("You have chosen the Cat!");
                        userPet = "cat";
                    }
                    else{
                        System.out.println("Invalid input");
                    }
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
    }
}