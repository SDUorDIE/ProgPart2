
package com.mycompany.progpart2;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class handler {
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    
    public void proccessMessages(int total){
        for(int i = 1; i<= total; i++){
            String recipient = javax.swing.JOptionPane.showInputDialog("Enter number:");
            String Text = javax.swing.JOptionPane.showInputDialog("enter message(250 max characatrers:");
            
            if (Text.length()>250){
                javax.swing.JOptionPane.showInputDialog(null, "message exceeds 250 characters by "+ (Text.length() - 250));
                i--; continue;
            }
            Message msg = new Message(i, recipient, Text);
            if (!msg.checkRecipientCell()){
                javax.swing.JOptionPane.showMessageDialog(null, "invalid phone number format.");
                i--; continue;
            }
            String action = msg.getSendOption();
            
            if (action.equals("send")){
                sentMessages.add(msg);
                javax.swing.JOptionPane.showMessageDialog(null, msg.toDisplayString()+ "\nMessage successfully sent");
            }else if (action.equals("store")){
                storedMessages.add(msg);
                javax.swing.JOptionPane.showMessageDialog(null, "message stored");
            }else{
                javax.swing.JOptionPane.showMessageDialog(null, "message disregarded");
            }
        }
        java.swing.JOptionPane.showMessageDialog(null, "total messages sent:"+ sentMessages.size());
    }
    public void storeMessagesToJson(){
        JSONArray jsonMessages = new JSONArray();
        for (Message msg : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("messageID", msg.getMessageID());
            obj.put("Hash",msg.getHash());
            obj.put("Recipient",msg.getRecipient());
            obj.put("Text",msg.getText());
            jsonMessages.add(obj);
        }
        try (FileWriter file = new FileWriter("storedMessages.json")){
            file.write(jsonMessages.toJSONString());
            file.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
