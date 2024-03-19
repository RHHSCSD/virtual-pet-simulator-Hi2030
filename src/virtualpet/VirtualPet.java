/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.util.Scanner;

public class VirtualPet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("    _____        ^----^ \n / /^ . ^\\ \\    |^ Y ^| \n  / (_U_) \\    //      \\\\  \n /	   \\   (,,) (,,)");
        System.out.print("       Pet Adventures! \n1.Start   2.Instructions   3.Exit \nWhere do you want to go? Enter number: ");
        int menuOption = input.nextInt();
        switch(menuOption){
            case 1: 
                System.out.print("Option 1: Dog   Option 2: Cat \nChoose a pet: ");
                int petChoice = input.nextInt();
                if(petChoice == 1){
                    System.out.println("You have chosen the Dog!");
                }
                else if(petChoice == 2){
                    System.out.println("You have chosen the Cat!");
                }
                else{
                    System.out.println("Invalid input");
                }
            case 2: System.out.println("Instructions"); break;
            case 3: System.exit(0); break;
            default: System.out.println("Invalid input");
        }
    }
}