package com.mycompany.progpart2;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class handles sending and storing messages.
 * JSON functionality uses json-simple:
 * 
 * Attribution:
 * https://code.google.com/archive/p/json-simple/
 * 
 * Maven Dependency:
 * <dependency>
 *   <groupId>com.googlecode.json-simple</groupId>
 *   <artifactId>json-simple</artifactId>
 *   <version>1.1.1</version>
 * </dependency>
 */
public class Handler {
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();

    public void processMessages(int total) {
        for (int i = 1; i <= total; i++) {
            String recipient = javax.swing.JOptionPane.showInputDialog("Enter number:");
            String text = javax.swing.JOptionPane.showInputDialog("Enter message (250 max characters):");

            if (text.length() > 250) {
                javax.swing.JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (text.length() - 250));
                i--;
                continue;
            }

            Message msg = new Message(i, recipient, text);

            if (!msg.checkRecipientCell()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Invalid phone number format.");
                i--;
                continue;
            }

            String action = msg.getSendOption();

            switch (action) {
                case "send":
                    sentMessages.add(msg);
                    javax.swing.JOptionPane.showMessageDialog(null, msg.toDisplayString() + "\nMessage successfully sent.");
                    break;
                case "store":
                    storedMessages.add(msg);
                    javax.swing.JOptionPane.showMessageDialog(null, "Message stored.");
                    break;
                default:
                    javax.swing.JOptionPane.showMessageDialog(null, "Message disregarded.");
            }
        }

        javax.swing.JOptionPane.showMessageDialog(null, "Total messages sent: " + sentMessages.size());
    }

    public void storeMessagesToJson() {
        JSONArray jsonMessages = new JSONArray();
        for (Message msg : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("messageID", msg.getMessageID());
            obj.put("Hash", msg.getHash());
            obj.put("Recipient", msg.getRecipient());
            obj.put("Text", msg.getText());
            jsonMessages.add(obj);
        }
        try (FileWriter file = new FileWriter("storedMessages.json")) {
            file.write(jsonMessages.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readMessagesFromJson() {
        try (FileReader reader = new FileReader("storedMessages.json")) {
            JSONParser parser = new JSONParser();
            JSONArray jsonMessages = (JSONArray) parser.parse(reader);

            storedMessages.clear(); 

            for (Object obj : jsonMessages) {
                JSONObject jsonObj = (JSONObject) obj;
                String id = (String) jsonObj.get("messageID");
                String hash = (String) jsonObj.get("Hash");
                String recipient = (String) jsonObj.get("Recipient");
                String text = (String) jsonObj.get("Text");

                Message msg = new Message(Integer.parseInt(id), recipient, text);
                storedMessages.add(msg);
            }

            javax.swing.JOptionPane.showMessageDialog(null, "Stored messages loaded from JSON.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error reading JSON: " + e.getMessage());
        }
    }
}
