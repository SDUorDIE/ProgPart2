
package com.mycompany.progpart2;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class ProgPart2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user to enter their username
            System.out.print("Enter your username (must contain '_' and be no more than 5 characters): ");
            String userId = scanner.nextLine();
            
            //prompt user to enter their password
            System.out.print("Enter your password (min 8 chars, uppercase, number, special char): ");
            String password = scanner.nextLine();
            
            //prompt user to enter their phone number 
            System.out.print("Enter your phone number (include international code, e.g. +27XXXXXXXXX): ");
            String phoneNum = scanner.nextLine();
            
            //promt user to enter their first name
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            
            //prompt user to enter their last name
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();

            User user = new User(userId, password, phoneNum, firstName, lastName);

            // Registration output
            String result = user.registerUser();
            System.out.println(result);

            //login if registration successful
            if (result.contains("successfully")) {
                System.out.print("Please login.\nUsername: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Password: ");
                String loginPassword = scanner.nextLine();

                if (user.loginUser(loginUsername, loginPassword)) {
                    System.out.println("Welcome " + firstName + ", " + lastName + " it is great to see you again.");
                } else {
                    System.out.println("Username or password incorrect, please try again.");//error handling 
                }
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static void showMainMenu(){
        boolean quit = false;
        
        while (!quit){
            String option = JOptionPane.showInputDialog(
            "welcome to QuickChat.\n\n" +
                    "choose an option:\n" +
                    "1) Send message\n" +
                    "2) show recently sent messages\n" +
                    "3)quit");
            
            switch(option) {
                case "1":
                    JOptionPane.showMessageDialog(null, "send message feature coming soon....");
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case "3":
                    quit = true;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please select 1, 2 or 3");
            }
                    
            
        }
    }
}