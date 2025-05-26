
package com.mycompany.progpart2;
import java.util.Random;

public class Message {
    priavte String messageID;
    private int messageNumber;
    private String recipient;
    private String text;
    private String Hash;
    
    public Message(int messageNumber, String recipient, String text){
       this.messageID = generateMessageID();
       this.messageNumber = messageNumber;
       this.recipient = recipient;
       this.text = text;
       this.Hash = createMessageHash;
    }
    private String generateMessageID(){
       Random rand = new Random();
       StringBuilder id = new StringBuilder();
       for (int i = 0; i < 10; i++) {
           id.append(rand.nextInt(10));
       }
       return id.toString();
    }
    public boolean checkMessageID(){
        return this.messageID.length() == 10;
    }
    public boolean checkRecipientCell(){
        return recipient.matches("^\\+\\d{10,12}$");
    }
    public String getSendOption(){
        String[] choices = {"Send", "Disregard", "store"};
        int choice = javax.swing.JOptionPane.showOptionDialog(null,
                "choose an action",
                "send Message",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                null,
                choices,
                choices[0]);
        
        switch (choice) {
            case 0: return "send";
            case 1: return "Disregard";
            case 2: return "store";
            default: return "disregard";
        }
    }
    public String toDisplayString(){
        return "Mesaage ID: " + messageID +
                "\nHash: " + Hash +
                "\nRecipient: " + recipient +
                "\nMessage: " + text;
    }
    public String getMessageID() {return messageID;}
    public String getHash() {return Hash;}
    public String getRecipient() {return recipient;}
    public String getText() {return text;}
}
