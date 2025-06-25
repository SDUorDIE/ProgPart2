package com.mycompany.progpart2;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        // REGISTRATION
        String username = JOptionPane.showInputDialog("Enter username (must contain _ and be ≤ 5 chars):");
        String password = JOptionPane.showInputDialog("Enter password (min 8 chars, 1 uppercase, 1 digit, 1 special char):");
        String phone = JOptionPane.showInputDialog("Enter phone number (e.g. +27831234567):");
        String first = JOptionPane.showInputDialog("Enter first name:");
        String last = JOptionPane.showInputDialog("Enter last name:");

        User user = new User(username, password, phone, first, last);
        String result = user.registerUser();
        JOptionPane.showMessageDialog(null, result);

        if (!result.contains("successfully completed")) {
            JOptionPane.showMessageDialog(null, "Registration failed. Exiting app.");
            return;
        }

        // LOGIN
        String loginUser = JOptionPane.showInputDialog("Enter your username to login:");
        String loginPass = JOptionPane.showInputDialog("Enter your password to login:");

        if (!user.loginUser(loginUser, loginPass)) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting app.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome " + user.getFirstName() + "!");

        // MESSAGING MENU
        Handler handler = new Handler();
        boolean quit = false;

        while (!quit) {
            String choice = JOptionPane.showInputDialog("""
                Choose an option:
                1) Send Messages
                2) Show Stored Messages
                3) Save Messages to JSON
                4) Load Messages from JSON
                5) Quit
            """);

            switch (choice) {
                case "1" -> {
                    int count = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
                    handler.processMessages(count);
                }
                case "2" -> handler.showReport();
                case "3" -> handler.storeMessagesToJson();
                case "4" -> handler.readMessagesFromJson();
                case "5" -> quit = true;
                default -> JOptionPane.showMessageDialog(null, "Invalid input. Choose a number from 1–5.");
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
    }
}
