
package com.mycompany.progpart2;
import javax.swing.JOptionPane;
public class main {
    public static void main(String[] args){
        JOptionPane.showMessageDialog(null, "Welcom to quickchat");
        
        boolean loggedIn = true; 
        if (loggedIn){
            boolean quit = false;
            Handler handler = new Handler();
            
            while (!quit){
                String choice = JOptionPane.showInputDialog("choose an option:\n1 send Messages\n2 Show recently sent messages\n3) Quit");
                
                switch (choice) {
                    case "1":
                        int count =Integer.parseInt(JOptionpane.showInputDialog("how many messages do you want to send?"));
                        handler.processMessages(count);
                        handler.storeMessagesToJson;
                        break;
                        
                    case "2":
                        JOptionPane.showMessageDialog(null, "coming soon");
                        break;
                        
                    case "3":
                        quit = true;
                        break;
                        
                    default:
                        JOptionPane.showMessagesDialog(null, "Invalid input. choose 1, 2 or 3");
                }
            }
        }
    }
}
